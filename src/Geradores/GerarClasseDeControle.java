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
public class GerarClasseDeControle {

    String projetoDestino;
    String nomeClasse;

    public GerarClasseDeControle(String projetoDestino, String nomeClasse) {
        this.projetoDestino = projetoDestino;
        this.nomeClasse = nomeClasse;
        gerarClasseControle();
    }

    private void gerarClasseControle() {
        Ferramentas ferramentas = new Ferramentas();

        List<String> arquivoBase = ferramentas.abrirArquivo("src/Main/" + nomeClasse + ".txt");

        List<String> codigoGerado = new ArrayList<>();

        //fazer a classe de controle de lista
        codigoGerado.clear();
        codigoGerado.add("package Main;\n"
                + "\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;");
        //import java.util.Date;
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            if (aux[0].equals("Date")) {
                codigoGerado.add("import java.util.Date;");
                break;
            }
        }

        codigoGerado.add("public class " + nomeClasse + "Controle {");
        codigoGerado.add("  List<" + nomeClasse + "> lista = new ArrayList<>();");
        codigoGerado.add("  public " + nomeClasse + "Controle() {\n"
                + "   \n"
                + "    }");

        //contrutor vazio
        codigoGerado.add("");

        //metodo buscar
        String tipoChave = arquivoBase.get(0).split(";")[0];
        String nomeChave = arquivoBase.get(0).split(";")[1];

        codigoGerado.add("  public " + nomeClasse + " buscar(" + tipoChave + " chave) {\n"
                + "        for (int i = 0; i < lista.size(); i++) {");
        String aux = "";
        switch (tipoChave) {
            case "int":
                aux = " if (chave==lista.get(i).get" + ferramentas.plMaius(nomeChave) + "()) {";
                break;
            case "String":
                aux = " if (chave.equals(lista.get(i).get" + ferramentas.plMaius(nomeChave) + "())) {";
                break;
            default:
                aux = "tipo desconhecido, programe";
        }

        codigoGerado.add(aux);
        codigoGerado.add("return lista.get(i);//se encontrou, retorna a linha toda (um contato)\n"
                + "            }\n"
                + "        }\n"
                + "        return null; //se não encontrou na lista, retorna um contato nulo\n"
                + "    }\n"
                + ""
        );

        //método inserir
        codigoGerado.add("    public void inserir(" + nomeClasse + " " + ferramentas.plMinus(nomeClasse) + ") {\n"
                + "        lista.add(" + ferramentas.plMinus(nomeClasse) + ");\n"
                + "    }\n");


        //método alterar
        /**
         * *
         */        codigoGerado.add("    void alterar(" + nomeClasse + " " + ferramentas.plMinus(nomeClasse) + "Original" + ", " + nomeClasse + " " + ferramentas.plMinus(nomeClasse) + "Alterado) {\n" + "        lista.set(lista.indexOf(" + ferramentas.plMinus(nomeClasse) + "Original), " + ferramentas.plMinus(nomeClasse) + "Alterado);\n"
                + "    }\n"
                + "    public List<String> listar() {\n"
                + "        List<String> ls = new ArrayList<>();\n"
                + "        for (int i = 0; i < lista.size(); i++) {\n"
                + "            ls.add(\"\"\n"
        );
        for (String s : arquivoBase) {
            String aux1[] = s.split(";");
            codigoGerado.add("                    + lista.get(i).get" + ferramentas.plMaius(aux1[1]) + "() + \";\""
            );
        }
        codigoGerado.add("            );\n"
                + "        }\n"
                + "        return ls;\n"
                + "    }\n");

        //método excluir
        codigoGerado.add("public void excluir(" + nomeClasse + " " + ferramentas.plMinus(nomeClasse) + "){\n"
                + "        lista.remove(" + ferramentas.plMinus(nomeClasse) + ");\n"
                + "    }\n");

        codigoGerado.add("\n}");

        //....
        String cc = projetoDestino + "/src/Main/" + nomeClasse + "Controle.java";
        System.out.println("Vai criar a classe nesse caminho=> " + cc);
        ferramentas.salvarArquivo(cc, codigoGerado);

    }

}
