package jogo.logica;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SaveAndLoad {

        private static final String path_save= ".\\ficheiros\\saves\\";

        private ArrayList<File> savesArray;
        private  File f;

        public SaveAndLoad() {
            this.savesArray = new ArrayList<>();
            f = new File(path_save);
            f.mkdir();//cria a diretoria dos saves na pasta ficheiros

        }

        public  boolean saveToFile(String filename, Object obj){ // recebe nome do ficheiro e o objeto (jogo gestao)

            ObjectOutputStream oos = null;

            savesArray.clear();
            savesArray.addAll(Arrays.asList(f.listFiles()));// buscar os ficheiros dentro da diretoria path_save para o savesArray

            File aux = null;

            for (File s : savesArray) {
                if (s.getName().equals(filename))
                    aux = s;/// pesquisar pra saber se ha outro fich com o mesmo nome
            }
            if(aux != null)
                return false;

            try {
                oos = new ObjectOutputStream(new FileOutputStream(path_save +filename));
                oos.writeObject(obj);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            finally {
                try {
                    if(oos!=null)
                        oos.close();
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
            return true;
        }


        public  Object loadFromFile(String filename){ // recebe nome do ficheiro e o objeto (jogo gestao)


            ObjectInputStream in = null;
            Object obj = null;

            savesArray.clear();
            savesArray.addAll(Arrays.asList(f.listFiles()));

            File aux = null;

            for (File s : savesArray) {
                if (s.getName().equals(filename))
                    aux = s;//pesquisar pra saber se fich existe
            }

            if (aux == null)
                return null;
            try {
                in = new ObjectInputStream(new FileInputStream(aux));
                obj = in.readObject();
                if (in != null)
                    in.close();
                return obj;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (in != null)
                        in.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            return  obj;
        }
}
