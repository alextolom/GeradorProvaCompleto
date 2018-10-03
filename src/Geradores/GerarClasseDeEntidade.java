/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import java.util.ArrayList;
import java.util.List;
import myTools.Ferramentas;

/**
 *
 * @author radames
 */
public class GerarClasseDeEntidade {

    String projetoDestino;
    String nomeClasse;

    public GerarClasseDeEntidade(String projetoDestino, String nomeClasse) {
        this.projetoDestino = projetoDestino;
        this.nomeClasse = nomeClasse;
        gerarClasseEntidade();
    }

    private void gerarClasseEntidade() {
        Ferramentas ferramentas = new Ferramentas();

        List<String> arquivoBase = ferramentas.abrirArquivo("src/Main/" + nomeClasse + ".txt");

        List<String> codigoGerado = new ArrayList<>();

        codigoGerado.add("package Main;");

        //import java.util.Date;
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            if (aux[0].equals("Date")) {
                codigoGerado.add("import java.util.Date;\n"
                        + "import java.text.SimpleDateFormat;");
                break;
            }
        }

        codigoGerado.add("public class " + nomeClasse + " {");

        //atributos
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            codigoGerado.add("private " + aux[0] + " " + aux[1] + ";");
        }
        codigoGerado.add("public "+ferramentas.plMaius(nomeClasse)+"() {\n" +
"    }");
//        codigoGerado.add("public "+ferramentas.plMaius(nomeClasse)+"(");
        String ss1 = "";
        
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            ss1 += aux[0] + aux[1] + ",";
//            codigoGerado.add(aux[0] + " " + aux[1] + ",");
        }ss1 = ss1.substring(0, ss1.length() - 1);
        codigoGerado.add("public "+ferramentas.plMaius(nomeClasse)+"("+ss1+"){");
        
        
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            codigoGerado.add("this."+aux[1]+ " = " +aux[1]+";");
        }
        codigoGerado.add("}");
        
//        for (int i = 0; i < codigoGerado.size(); i++) {
//            System.out.println(codigoGerado.get(i));
//        }
        codigoGerado.add("");

        //métodos get
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            codigoGerado.add(""
                    + "public " + aux[0] + " get" + ferramentas.plMaius(aux[1]) + "() {\n"
                    + "        return " + aux[1] + ";\n"
                    + "    }\n");
        }
        codigoGerado.add("");

        //métodos set
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            codigoGerado.add(" public void set" + ferramentas.plMaius(aux[1])
                    + "(" + aux[0] + "  " + aux[1] + ") {\n"
                    + "        this. " + aux[1] + " =  " + aux[1] + ";\n"
                    + "    }");
        }

        codigoGerado.add("SimpleDateFormat simpleDateFormat = new SimpleDateFormat();\n"
                + "      \n"
                + "public String converteDeDateParaString(Date data) {\n"
                + "\n"
                + "       try {\n"
                + "\n"
                + "           return simpleDateFormat.format(data); //converte a data para string\n"
                + "\n"
                + "       } catch (Exception e) {\n"
                + "\n"
                + "           return null;//se algo estiver errado na data, retorne null\n"
                + "\n"
                + "           //tem que tratar o erro na classe que chamou\n"
                + "\n"
                + "       }\n"
                + "\n"
                + "   }");
        
        
        //Método toString
        String ss = "";
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            if (aux[0].equals("Date")) {
                ss += "converteDeDateParaString("+aux[1]+")" + "+ \";\"+";
            }else{
                ss += aux[1] + "+ \";\"+";
            }
            
            }
        
        ss = ss.substring(0, ss.length() - 1);

        codigoGerado.add(" @Override\n"
                + "    public String toString() {\n"
                + "        return " + ss + " ;\n"
                + "    }");

        codigoGerado.add("");
        codigoGerado.add("}");

        String cc = projetoDestino + "/src/Main/" + nomeClasse + ".java";
        System.out.println("Vai criar a classe nesse caminho=> " + cc);
        ferramentas.salvarArquivo(cc, codigoGerado);

        //terminou a classe de entidade
    }

}
