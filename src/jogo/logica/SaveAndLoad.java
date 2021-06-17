package jogo.logica;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SaveAndLoad {

        private static final String path_save= "";

        private ArrayList<File> savesArray;
        private  File f;

        public SaveAndLoad() {
            this.savesArray = new ArrayList<>();
            f = new File(path_save);
            f.mkdir();//cria a diretoria dos saves na pasta ficheiros

        }

        public  boolean saveToFile(File file, Object obj){ // recebe nome do ficheiro e o objeto (jogo gestao)


            ObjectOutputStream oos = null;

            try {
                oos = new ObjectOutputStream(new FileOutputStream(path_save + file));
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


        public  Object loadFromFile(File file){ // recebe nome do ficheiro e o objeto (jogo gestao)


            ObjectInputStream in = null;
            Object obj = null;

            try {
                in = new ObjectInputStream(new FileInputStream(file));
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
