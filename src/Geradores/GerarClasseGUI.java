package Geradores;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import myTools.Ferramentas;

/**
 *
 * @author radames
 */
public class GerarClasseGUI {

    String projetoDestino;
    String nomeClasse;

    public GerarClasseGUI(String projetoDestino, String nomeClasse) {
        this.projetoDestino = projetoDestino;
        this.nomeClasse = nomeClasse;
        gerarClasseGUI();
    }

    private void gerarClasseGUI() {
        Ferramentas ferramentas = new Ferramentas();

        List<String> arquivoBase = ferramentas.abrirArquivo("src/Main/" + nomeClasse + ".txt");

        List<String> codigoGerado = new ArrayList<>();

        //fazer a classe de gui de lista
        codigoGerado.clear();
        codigoGerado.add("package Main;\n"
                + "\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.CardLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.FlowLayout;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.util.List;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JScrollPane;\n"
                + "import javax.swing.JTable;\n"
                + "import javax.swing.JTextArea;\n"
                + "import javax.swing.JTextField;\n"
                + "import javax.swing.table.DefaultTableModel;\n"
                + "import javax.swing.text.DefaultCaret;\n"
                + "import myTools.Ferramentas;\n"
                + "import java.text.SimpleDateFormat;\n"
                + "import myTools.UsarGridBagLayout;");
        //import java.util.Date;
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            if (aux[0].equals("Date")) {
                codigoGerado.add("import java.util.Date;");
                break;
            }
        }
        String tipoChave = arquivoBase.get(0).split(";")[0];
        String nomeChave = arquivoBase.get(0).split(";")[1];
        String nomeChave3 = arquivoBase.get(1).split(";")[1];

        codigoGerado.add("class " + ferramentas.plMaius(nomeClasse) + "GUI extends JFrame {"
                + "private Container cp;\n"
                + "    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));\n"
                + "    private JPanel pnCentro = new JPanel();\n"
                + "    private CardLayout cardLayout = new CardLayout();\n"
                + "    private JPanel pnSul = new JPanel(cardLayout);\n"
                + "    private JPanel pnSulMsg = new JPanel();\n"
                + "    private JPanel pnSulListagem = new JPanel(new GridLayout(1, 1));\n"
                + "    private JButton btBuscar = new JButton(\"<html><center>\"\n"
                + "                 + \"<font color=#000fdd>Buscar</font>\");\n"
                + "    private JButton btInserir = new JButton(\"Inserir\");\n"
                + "    private JButton btAlterar = new JButton(\"Alterar\");\n"
                + "    private JButton btExcluir = new JButton(\"Excluir\");\n"
                + "    private JButton btListar = new JButton(\"Listar\");\n"
                + "    private JButton btSalvar = new JButton(\"Salvar\");\n"
                + "    private JButton btCancelar = new JButton(\"Cancelar\");\n"
                + "    private JButton btGravar = new JButton(\"Gravar\");\n"
                + "    String[] colunas = new String[]{");
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            codigoGerado.add("\"" + aux[1] + "\",");
        }
        codigoGerado.add("};");
        codigoGerado.add("    String[][] dados = new String[0][" + arquivoBase.size() + "];");

        
        String nomeChavoso = "";
        if (tipoChave.equals("Date")) {
            nomeChavoso = "    private DateTextField tf" + ferramentas.plMaius(nomeChave) + " = new DateTextField();";
        }else{
            nomeChavoso = "    private JTextField tf" + ferramentas.plMaius(nomeChave) + " = new JTextField(5);";
        }
        //gera o primeiro tf
        for (String s : arquivoBase) {
            String aux1[] = s.split(";");
            codigoGerado.add(nomeChavoso);
            break;
        }

        //gera o primeiro lb
        for (String s : arquivoBase) {
            String aux2[] = s.split(";");
            codigoGerado.add("    private JLabel lb" + ferramentas.plMaius(nomeChave) + " = new JLabel(\"" + ferramentas.plMaius(nomeChave) + "\");");
            break;
        }

        //GERADORES DE TEXTFIELD E LABELS
        int c = arquivoBase.size();
        for (int i = 1; i < c; i++) {
            String nomeChave1 = arquivoBase.get(i).split(";")[1];//pegando os nomes dos parametros
            String nomeChave2 = arquivoBase.get(i).split(";")[0];//pegando os tipos dos parametros
            if (nomeChave2.equals("Date")) {
            codigoGerado.add("    private DateTextField tf" + ferramentas.plMaius(nomeChave1) + " = new DateTextField();");
            codigoGerado.add("    private JLabel lb" + ferramentas.plMaius(nomeChave1) + " = new JLabel(\"" + ferramentas.plMaius(nomeChave1) + "\");");
            }else{
            codigoGerado.add("    private JTextField tf" + ferramentas.plMaius(nomeChave1) + " = new JTextField(5);");
            codigoGerado.add("    private JLabel lb" + ferramentas.plMaius(nomeChave1) + " = new JLabel(\"" + ferramentas.plMaius(nomeChave1) + "\");");
        }
        }
//        //gerador textfield
//        for (String s : arquivoBase) {
//            String aux1[] = s.split(";");
//            
//            String[] colunas = new String[]{aux1[1]};
//                        codigoGerado.add("    private JTextField tf" + ferramentas.plMaius(colunas[0]) + " = new JTextField(5);");
//        }
//        //gerador labels
//        for (String s : arquivoBase) {
//            String aux2[] = s.split(";");
//            codigoGerado.add("    private JLabel lb" + ferramentas.plMaius(aux2[1]) + " = new JLabel(\"" + ferramentas.plMaius(aux2[1]) + "\");");
//        }

        codigoGerado.add("DefaultTableModel model = new DefaultTableModel(dados, colunas);\n"
                + "    JTable tabela = new JTable(model);\n"
                + "\n"
                + "    JScrollPane scrollList = new JScrollPane();\n"
                + "\n"
                + "    private JScrollPane scrollMensagem = new JScrollPane(); //barra de rolagem\n"
                + "\n"
                + "    JTextArea textAreaMsg = new JTextArea(5, 150); //campo para texto com várias linhas\n"
                + "\n"
                + "    private boolean inserindo; //esta variável controla se é uma operação de INSERT ou UPDATE no botão salvar\n"
                + "\n"
                + "    private " + ferramentas.plMaius(nomeClasse) + "Controle controle = new " + ferramentas.plMaius(nomeClasse) + "Controle(); //essa é a classe de processamento (controle da lista de contatos)\n"
                + "    private " + ferramentas.plMaius(nomeClasse) + " contato = new " + ferramentas.plMaius(nomeClasse) + "(); //ver classe contato\n"
                + "\n"
                + "    DefaultCaret caret = (DefaultCaret) textAreaMsg.getCaret(); //para que haja rolagem automática do textArea\n"
                + "    UsarGridBagLayout usarGridBagLayoutCentro = new UsarGridBagLayout(pnCentro);\n"
                + "\n"
                + "    Ferramentas fer = new Ferramentas();\n"
                + "SimpleDateFormat data = new SimpleDateFormat(\"dd/MM/yyyy\");");

        codigoGerado.add("    private void setLog(String msg) {\n"
                + "        textAreaMsg.append(msg + \"\\n\");\n"
                + "        textAreaMsg.setCaretPosition(textAreaMsg.getDocument().getLength());\n"
                + "    }");

        codigoGerado.add("    private void limparValoresDosAtributos() {");
        for (int i = 1; i < c; i++) {
            String nomeChave1 = arquivoBase.get(i).split(";")[1];
            codigoGerado.add("tf" + ferramentas.plMaius(nomeChave1) + ".setText(\"\");");
        }
        codigoGerado.add("}");

        codigoGerado.add("    private void travarTextFields(boolean campo) {\n"
                + "        tf" + ferramentas.plMaius(nomeChave) + ".setEditable(campo); //permite que o usuario digite nesse textField");
        for (int i = 1; i < c; i++) {
            String nomeChave1 = arquivoBase.get(i).split(";")[1];
            codigoGerado.add("tf" + ferramentas.plMaius(nomeChave1) + ".setEditable(!campo);");
        }
        codigoGerado.add("        if (!campo) {\n"
                + "            tf" + ferramentas.plMaius(nomeChave3) + ".requestFocus();\n"
                + "tf" + ferramentas.plMaius(nomeChave3) + ".selectAll();"
                + "}"
                + "}");

        codigoGerado.add("SimpleDateFormat simpleDateFormat = new SimpleDateFormat(\"dd/MM/yyyy\");\n"
                + "    public Date converteDeStringParaDate(String s) {\n"
                + "\n"
                + "       try {\n"
                + "\n"
                + "           return simpleDateFormat.parse(s);//converte\n"
                + "\n"
                + "       } catch (Exception e) {\n"
                + "\n"
                + "           return null;// se algo estiver errado, retorne null\n"
                + "\n"
                + "       }\n"
                + "\n"
                + "   }");
        codigoGerado.add("public String converteDeDateParaString(Date data) {\n"
                + "        try {\n"
                + "            return simpleDateFormat.format(data);\n"
                + "        } catch (Exception e) {\n"
                + "            return null;\n"
                + "        }\n"
                + "    }");

        //Começa construtor GUI
        codigoGerado.add("    public " + ferramentas.plMaius(nomeClasse) + "GUI() {\n"
                + "        //abrir o arquivo\n"
                + "        List<String> listaAuxiliar = fer.abrirArquivo(\"" + ferramentas.plMaius(nomeClasse) + ".txt\");");
        codigoGerado.add("if (listaAuxiliar != null) {//se o arquivo existe, copia os dados\n"
                + "            for (int i = 0; i < listaAuxiliar.size(); i++) {\n"
                + "                String aux[] = listaAuxiliar.get(i).split(\";\");\n"
                + "                " + ferramentas.plMaius(nomeClasse) + " c = new " + ferramentas.plMaius(nomeClasse) + "(");

        String ler = "";
        String ss = "";
        for (int i = 0; i < c; i++) {
            String tipo, nome;
            String[] aux = arquivoBase.get(i).split(";");
            tipo = aux[0];
            ss += tipo + ",";
            nome = aux[1];
            switch (tipo) {
                case "long":
                    if (i != c) {
                        ler += "Long.valueOf(aux[" + i + "]), ";
                    }
                    break;
                case "double":
                    if (i != c) {
                        ler += "Double.valueOf(aux[" + i + "]), ";
                    }
                    break;
                case "boolean":
                    if (i != c) {
                        ler += "Boolean.valueOf(aux[" + i + "]), ";
                    }
                    break;
                case "int":
                    if (i != c) {
                        ler += "Integer.valueOf(aux[" + i + "]), ";
                    }
                    break;
                case "Date":
                    if (i != c) {
                        ler += "converteDeStringParaDate(aux[" + i + "]), ";
                    }
                    break;
                case "String":
                    if (i != c) {
                        ler += "aux[" + i + "], ";
                    }
                    break;
            }
            ler = ler.substring(0, ler.length() - 1);
        }
//        ler = ler.substring(0, ler.length() - 1);
        codigoGerado.add(ler);
//        for (int i = 0; i < c; i++) {
//            codigoGerado.add("aux[" + i + "], ");
//        }
        codigoGerado.add(");");
        codigoGerado.add("controle.inserir(c);\n"
                + "            }\n"
                + "        }");
        codigoGerado.add("        //faz com que a última linha do \n"
                + "        //jTextArea seja exibida\n"
                + "        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);\n"
                + "        scrollMensagem.setViewportView(textAreaMsg);\n"
                + "        scrollMensagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//esconde a barra horizontal\n"
                + "\n"
                + "        setDefaultCloseOperation(DISPOSE_ON_CLOSE);\n"
                + "\n"
                + "        cp = getContentPane();\n"
                + "        cp.setLayout(new BorderLayout());\n"
                + "        cp.add(pnNorte, BorderLayout.NORTH);\n"
                + "        cp.add(pnCentro, BorderLayout.CENTER);\n"
                + "        cp.add(pnSul, BorderLayout.SOUTH);");

        codigoGerado.add("pnNorte.add(lb" + ferramentas.plMaius(nomeChave) + ");\n"
                + "        pnNorte.add(tf" + ferramentas.plMaius(nomeChave) + ");\n"
                + "        pnNorte.add(btBuscar);\n"
                + "        pnNorte.add(btInserir);\n"
                + "        pnNorte.add(btAlterar);\n"
                + "        pnNorte.add(btExcluir);\n"
                + "        pnNorte.add(btSalvar);\n"
                + "        pnNorte.add(btCancelar);\n"
                + "        pnNorte.add(btListar);");

        for (int i = 1; i < c; i++) {
            String nomeChave1 = arquivoBase.get(i).split(";")[1];
            codigoGerado.add("usarGridBagLayoutCentro.add(lb" + ferramentas.plMaius(nomeChave1) + "," + "tf" + ferramentas.plMaius(nomeChave1) + ");");
        }

        codigoGerado.add("UsarGridBagLayout usarGridBagLayoutSul = new UsarGridBagLayout(pnSulMsg);\n"
                + "        usarGridBagLayoutSul.add(new JLabel(\"log\"), scrollMensagem);\n"
                + "        pnSul.add(pnSulMsg, \"pnMsg\");\n"
                + "        pnSul.add(pnSulListagem, \"pnLst\");\n"
                + "\n"
                + "        pnSul.setBackground(Color.red);\n"
                + "\n"
                + "        btSalvar.setVisible(false);\n"
                + "        btCancelar.setVisible(false);\n"
                + "        btInserir.setVisible(false);\n"
                + "        btAlterar.setVisible(false);\n"
                + "        btExcluir.setVisible(false);");

        codigoGerado.add("        travarTextFields(true);\n"
                + "        textAreaMsg.setEditable(false);"
                + "        addWindowListener(new WindowAdapter() {\n"
                + "            @Override\n"
                + "            public void windowClosing(WindowEvent e) {\n"
                + "                btGravar.doClick();\n"
                + "                // Sai   \n"
                + "                dispose();\n"
                + "            }\n"
                + "        });");

        //BOTAO GRAVAR
        codigoGerado.add("btGravar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                //converter a lista<contato> em lista de string\n"
                + "                List<String> listaStr = controle.listar();\n"
                + "                fer.salvarArquivo(\"" + ferramentas.plMaius(nomeClasse) + ".txt\", listaStr);");
        codigoGerado.add("}\n"
                + "        });");

        String auxTipoChave = "";
//        String auxTipoChave1 = "";
        for (int i = 1; i < c; i++) {
            String nomeChave1 = arquivoBase.get(i).split(";")[0];

            //para ficar mais elegante, mudei de if para switch
            switch (nomeChave1) {
                case "Date":
                    auxTipoChave = "new SimpleDateFormat(\"dd/MM/yyyy\").format";
                    break;
                case "double":
                    auxTipoChave = "String.valueOf";
                    break;
                case "boolean":
                    auxTipoChave = "String.valueOf";
                    break;
                case "int":
                    auxTipoChave = "String.valueOf";
                    break;
                case "String":
                    auxTipoChave = "";
                    break;
                case "long":
                    auxTipoChave = "";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Tipo desconhecido \n"
                            + nomeChave1);
            }

        }
        
        String auxTipoChave1 = "";
        if (tipoChave.equals("Date")) {
            auxTipoChave1 = "converteDeDateParaString(tf"+ferramentas.plMaius(nomeChave)+".getText()));";
        }else if(tipoChave.equals("long")) {
            auxTipoChave1 = "Long.valueOf(tf"+ferramentas.plMaius(nomeChave)+".getText()));";
        }else if(tipoChave.equals("double")) {
            auxTipoChave1 = "Double.valueOf(tf"+ferramentas.plMaius(nomeChave)+".getText()));";
        }else if(tipoChave.equals("int")) {
            auxTipoChave1 = "Integer.valueOf(tf"+ferramentas.plMaius(nomeChave)+".getText()));";
        }else{
            auxTipoChave1 = "tf"+ferramentas.plMaius(nomeChave)+".getText());";
        }
/////////////////////////////BOTAO BUSCAR///////////////////////////////////////

        codigoGerado.add("        btBuscar.addActionListener((ActionEvent e) -> {\n"
                + "\n"
                + "            if (tf" + ferramentas.plMaius(nomeChave) + ".getText().trim().equals(\"\")) {\n"
                + "                JOptionPane.showMessageDialog(cp, \"Querido usuário, vazio não pode \");\n"
                + "            } else {\n"
                + "                try {\n"
                + "        tf" + ferramentas.plMaius(nomeChave) + ".setBackground(Color.green);\n"
                + "                    travarTextFields(true);\n"
                + "                    cardLayout.show(pnSul, \"pnMsg\");\n"
                + "                    contato = controle.buscar(" + auxTipoChave1
                + "                    if (contato == null) { //nao achou\n"
                + "                        btInserir.setVisible(true);\n"
                + "                        btAlterar.setVisible(false);\n"
                + "                        btExcluir.setVisible(false);\n"
                + "                        limparValoresDosAtributos();\n"
                + "                        setLog(\"Não achou na lista, pode inserir se quiser...\");\n"
                + "                    } else { //achou\n"
                + "                        btAlterar.setVisible(true);\n"
                + "                        btExcluir.setVisible(true);");
        
        for (int i = 0; i < c; i++) {
            String nomeChave1 = arquivoBase.get(i).split(";")[1];
            String nomeChave2 = arquivoBase.get(i).split(";")[0];
//        if (nomeChave2.equals("boolean")) {
//                codigoGerado.add(tfNm[i + 1] + ".setSelected(" + nomeClasse.toLowerCase() + ".get" + ferramentas.plMaius(nms[i + 1]) + "());");
//            }
            if (nomeChave2.equals("Date")) {
                codigoGerado.add("tf" + ferramentas.plMaius(nomeChave1) + ".setText(converteDeDateParaString(" + "contato" + ".get" + ferramentas.plMaius(nomeChave1) + "()));");
            } else {
                codigoGerado.add("tf" + ferramentas.plMaius(nomeChave1) + ".setText(String.valueOf(" + "contato" + ".get" + ferramentas.plMaius(nomeChave1) + "()));");
            }
        }

        codigoGerado.add("setLog(\"Achou [\" + contato.get" + ferramentas.plMaius(nomeChave) + "()+\"]\");");
        codigoGerado.add("}");
        codigoGerado.add("                } catch (Exception x) {\n"
                + "tf" + ferramentas.plMaius(nomeChave) + ".selectAll();\n"
                + "tf" + ferramentas.plMaius(nomeChave) + ".requestFocus();\n"
                + "tf" + ferramentas.plMaius(nomeChave) + ".setBackground(Color.red);"
                + "                    setLog(\"Erro no tipo de dados da chave. (\" + x.getMessage() + \")\");\n"
                + "                }//else\n"
                + "            }\n"
                + "            tf" + ferramentas.plMaius(nomeChave) + ".selectAll();\n"
                + "tf" + ferramentas.plMaius(nomeChave) + ".requestFocus();\n"
                + "        });");

////////////////////////////FIM DO BOTAO BUSCAR ///////////////////////////////
////////////////////////BOTAO SALVAR/////////////////////////////////
        codigoGerado.add("btSalvar.addActionListener((ActionEvent e) -> {");
        codigoGerado.add(ferramentas.plMaius(nomeClasse) + " " + ferramentas.plMinus(nomeClasse) + "Original = contato;");
        codigoGerado.add("if (inserindo) {\n"
                + "contato = new " + ferramentas.plMaius(nomeClasse) + "();\n"
                + "}");
        for (String s : arquivoBase) {
            String aux1[] = s.split(";");
            if (aux1[0].equals("long")) {
                codigoGerado.add("contato.set" + ferramentas.plMaius(aux1[1]) + "(Long.valueOf(tf" + ferramentas.plMaius(aux1[1]) + ".getText()));");
            }
            if (aux1[0].equals("double")) {
                codigoGerado.add("contato.set" + ferramentas.plMaius(aux1[1]) + "(Double.valueOf(tf" + ferramentas.plMaius(aux1[1]) + ".getText()));");
            }
            if (aux1[0].equals("Date")) {
                codigoGerado.add("contato.set" + ferramentas.plMaius(aux1[1]) + "(converteDeStringParaDate(tf" + ferramentas.plMaius(aux1[1]) + ".getText()));");
            }
            if (aux1[0].equals("int")) {
                codigoGerado.add("contato.set" + ferramentas.plMaius(aux1[1]) + "(Integer.valueOf(tf" + ferramentas.plMaius(aux1[1]) + ".getText()));");
            }
            if (aux1[0].equals("String")) {
                codigoGerado.add("contato.set" + ferramentas.plMaius(aux1[1]) + "(tf" + ferramentas.plMaius(aux1[1]) + ".getText());");
            }
            if (aux1[0].equals("Boolean")) {
                codigoGerado.add("contato.set" + ferramentas.plMaius(aux1[1]) + "(Boolean.valueOf(tf" + ferramentas.plMaius(aux1[1]) + ".getText()));");
            }
        }

//        codigoGerado.add("contato.set" + ferramentas.plMaius(nomeChave) + "(tf" + ferramentas.plMaius(nomeChave) + ".getText());");
//        for (int i = 1; i < c; i++) {
//            String nomeChave1 = arquivoBase.get(i).split(";")[1];
//            codigoGerado.add("contato.set" + ferramentas.plMaius(nomeChave1) + "(tf" + ferramentas.plMaius(nomeChave1) + ".getText());");
//        }
        codigoGerado.add("if (inserindo) { //a variavel inserindo é preenchida nos\n"
                + "controle.inserir(contato);\n"
                + "setLog(\"Inseriu [\" + contato.get" + ferramentas.plMaius(nomeChave) + "() +\"]\");\n"
                + "} else {\n"
                + "controle.alterar(" + ferramentas.plMinus(nomeClasse) + "Original, contato);\n"
                + "setLog(\"Alterou [\" + contato.get" + ferramentas.plMaius(nomeChave) + "() +\"]\");\n"
                + "}\n"
                + "//voltar para tela inicial\n"
                + "tf" + ferramentas.plMaius(nomeChave) + ".requestFocus();\n"
                + "tf" + ferramentas.plMaius(nomeChave) + ".selectAll();\n"
                + "btSalvar.setVisible(false);\n"
                + "btCancelar.setVisible(false);\n"
                + "btBuscar.setVisible(true);\n"
                + "btListar.setVisible(true);\n"
                + "limparValoresDosAtributos();\n"
                + "travarTextFields(true);");
        codigoGerado.add("});");
        codigoGerado.add("//**************** Cancelar alteração ou inclusão ********************\n"
                + "        btCancelar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                tf" + ferramentas.plMaius(nomeChave) + ".requestFocus();\n"
                + "tf" + ferramentas.plMaius(nomeChave) + ".selectAll();\n"
                + "btInserir.setVisible(false);\n"
                + "                btSalvar.setVisible(false);\n"
                + "                btCancelar.setVisible(false);\n"
                + "                btBuscar.setVisible(true);\n"
                + "                btListar.setVisible(true);\n"
                + "                travarTextFields(true);\n"
                + "                limparValoresDosAtributos();\n"
                + "                setLog(\"Cancelou a alteração ou inclusão do registro\");\n"
                + "            }\n"
                + "        });");

//############################# BOTAO ALTERAR #################################
        codigoGerado.add("btAlterar.addActionListener((ActionEvent e) -> {\n"
                + "            tf" + ferramentas.plMaius(nomeChave) + ".requestFocus();\n"
                + "            btSalvar.setVisible(true);\n"
                + "            btCancelar.setVisible(true);\n"
                + "            btBuscar.setVisible(false);\n"
                + "            btAlterar.setVisible(false);\n"
                + "            btExcluir.setVisible(false);\n"
                + "            btListar.setVisible(false);\n"
                + "            inserindo = false;\n"
                + "            travarTextFields(false);\n"
                + "            setLog(\"Alterando um registro existente\");\n"
                + "        });");

//||||||||||||||||||||||||||| BOTÃO INSERIR |||||||||||||||||||||||||||||||||||
        codigoGerado.add("btInserir.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                tf" + ferramentas.plMaius(nomeChave) + ".requestFocus();\n"
                + "                btInserir.setVisible(false);\n"
                + "                btSalvar.setVisible(true);\n"
                + "                btCancelar.setVisible(true);\n"
                + "                btBuscar.setVisible(false);\n"
                + "                btListar.setVisible(false);\n"
                + "                inserindo = true;\n"
                + "                travarTextFields(false);\n"
                + "                limparValoresDosAtributos();\n"
                + "                setLog(\"Inserindo um novo registro\");\n"
                + "            }\n"
                + "        });");

//======================= LISTAR =============================================
        codigoGerado.add("btListar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                cardLayout.show(pnSul, \"pnLst\");\n"
                + "                scrollList.setPreferredSize(tabela.getPreferredSize());\n"
                + "                pnSulListagem.add(scrollList);\n"
                + "                scrollList.setViewportView(tabela);\n"
                + "                List<String> listaDeContatos = controle.listar();//busca a lista de contatos\n"
                + "                String[] aux;\n"
                + "                colunas = new String[]{");
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            codigoGerado.add("\"" + aux[1] + "\",");
        }
        codigoGerado.add("};");
        codigoGerado.add("dados = new String[0][" + arquivoBase.size() + "];\n"
                + "model.setDataVector(dados, colunas);");
        codigoGerado.add("for (int i = 0; i < listaDeContatos.size(); i++) {\n"
                + "                    aux = listaDeContatos.get(i).split(\";\");\n"
                + "                    String[] linha = new String[]{");
        for (int i = 0; i < c; i++) {
            codigoGerado.add("aux[" + i + "], ");
        }
        codigoGerado.add("};model.addRow(linha);\n"
                + "                }\n"
                + "            }\n"
                + "        });");

//***************************** EXCLUIR *************************************
        codigoGerado.add("btExcluir.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                int dialogResult = JOptionPane.showConfirmDialog(cp, \"Vai excluir [ \"\n"
                + "                        + tf" + ferramentas.plMaius(nomeChave) + ".getText() + \"]?\", \"Exclui da lista\", NORMAL);\n");
        codigoGerado.add("if (dialogResult == JOptionPane.YES_OPTION) {\n"
                + "                    controle.excluir(contato);\n"
                + "                    setLog(\"Excluiu [\" + contato.get" + ferramentas.plMaius(nomeChave) + "() + \"]\");");
        codigoGerado.add("                }\n"
                + "            }\n"
                + "        });");

        codigoGerado.add("setSize(700, 500);\n"
                + "        setTitle(\"Crud contatos\");\n"
                + "        setLocationRelativeTo(null);\n"
                + "        tabela.setEnabled(false);\n"
                + "        setVisible(true);");

        codigoGerado.add("}");
        codigoGerado.add("\n}");

        //....
        String cc = projetoDestino + "/src/Main/" + nomeClasse + "GUI.java";
        System.out.println("Vai criar a classe nesse caminho=> " + cc);
        ferramentas.salvarArquivo(cc, codigoGerado);

    }

}
//commit