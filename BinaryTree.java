import java.util.Scanner;

// Cada nó representa uma pergunta.
// Cada ramo (esquerda ou direita) representa uma escolha binária (resposta) à pergunta.
// As folhas representam os resultados finais (super-herói).

// CLASSE NODE
class Node {
    String content;
    Node left = null;
    Node right = null;

    Node(String content) {
        this.content = content;
        this.left = null;
        this.right = null;
    }
}

// CLASSE ARVORE
public class BinaryTree {
    Node root = null;
    Scanner scanner = new Scanner(System.in); // ferramenta que lê info usuário

    public boolean isEmpty() {
        return root == null;
    }

    public void add(String content) {
        Node newNode = new Node(content);

        if (isEmpty()) {
            root = newNode;
            return;
        }

        insert(root, newNode);
    }

    // FUNÇÃO PRINT - imprime a árvore
    public void print(Node root) {
        if (root == null) 
            return;
        print(root.left);
        System.out.println(root.content);
        print(root.right);
    }

    // FUNÇÃO DE BUSCA - busca nó na árvore
    public boolean search(Node root, String search) {
        if (root.content.equals(search)) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return false;
        }

        if (root.content.compareTo(search) > 0 && root.left != null) {
            return search(root.left, search);
        }

        return search(root.right, search);
    }

    // FUNÇÃO DE REMOVER
    public String remove(Node root, String content) {
        if (root == null) return ""; //se tiver vazia

        if (root.left == null && root.right == null) // verifica se é folha
            return "";

        if (content == root.content) {
            // Verifica se o conteúdo é o do nó atual
            if (root.left == null) { // Caso 1: O nó tem apenas o filho direito ou nenhum
                return root.right == null ? "" : root.right.content;
            } else if (root.right == null) { // Caso 2: O nó tem apenas o filho esquerdo
                return root.left.content;
            } else {
                // Caso 3: O nó tem dois filhos
                Node parent = root;
                Node successor = root.right;
                while (successor.left != null) { // Encontra o menor valor na sub-árvore direita
                    parent = successor;
                    successor = successor.left;
                }
                root.content = successor.content; // Substitui o conteúdo pelo sucessor

                // Remove o sucessor que é garantidamente folha ou tem no máximo um filho (direito)
                if (parent.right == successor) {
                    parent.right = successor.right;
                } else {
                    parent.left = successor.right;
                }
            }
        }
        
        if (content.compareTo(root.content) > 0)
            return remove(root.right, content);
        else
            return remove(root.left, content);

    }

    // FUNÇAO DE INSERIR
    private void insert(Node root, Node newNode) {
        if (root.content.compareTo(newNode.content) > 0) {
            if (root.left == null) {
                root.left = newNode;
                return;
            }
            insert(root.left, newNode);
        }

        if (root.content.compareTo(newNode.content) < 0) {
            if (root.right == null) {
                root.right = newNode;
                return;
            }
            insert(root.right, newNode);
        }
    }

            // Cada nó representa uma pergunta.
            // Cada ramo (esquerda ou direita) representa uma escolha binária (resposta) à pergunta.
            // As folhas representam os resultados finais (super-herói).
    //WHILE VERIFICA RESPOSTA
    public void play() {
        Node current = root;
        while (true) {

            // verifica se há conteúdo
            // se não, escolhe caminho (1 ou 2)
            if (current.left == null && current.right == null) {
                System.out.println("\nAcredito que você se sairia bem como " + current.content + "!");
                break;
            } else {
                System.out.println(current.content + "\nEscolha entre 1 ou 2.");
                String response = scanner.nextLine(); // lê a resposta do usuário
                if (response.equalsIgnoreCase("1")) {
                    // esquerda
                    current = current.left;

				} else if (response.equalsIgnoreCase("2")) {
                    // direita
					current = current.right;

                } else {
                    System.out.println("\n\n[ATENÇÃO] Digite apenas 1 ou 2.");
                }
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree game = new BinaryTree();

        String asciiArt =
                "                         \n"
            + "             .  .          \n"
            + "             |\\_|\\         \n"
            + "             | a_a\\        \n"
            + "             | | \"]        \n"
            + "         ____| '-\\___      \n"
            + "        /.----.___.-'\\     \n"
            + "       //        _    \\    \n"
            + "      //   .-. (~v~) /|    \n"
            + "     |'|  /\\:  .--  / \\    \n"
            + "    // |-/  \\_/____/\\/~|   \n"
            + "   |/  \\ |  []_|_|_] \\ |   \n"
            + "   | \\  | \\ |___   _\\ ]_}  \n"
            + "   | |  '-' /   '.'  |     \n"
            + "   | |     /    /|:  |     \n"
            + "   | |     |   / |:  /\\    \n"
            + "   | |     /  /  |  /  \\   \n"
            + "   | |    |  /  /  |    \\  \n"
            + "   \\ |    |/\\/  |/|/\\    \\ \n"
            + "    \\|\\ |\\|  |  | / /\\/\\__\\\n"
            + "     \\ \\| | /   | |__      \n"
            + "          / |   |____)     \n"
            + "          |_/              \n";

        // cores para textos
        String colorReset = "\u001B[0m";
        String colorBlue = "\u001B[34m";
        String colorGreen = "\u001B[32m";
        String colorYellow = "\u001B[33m";
        String colorCyan = "\u001B[36m";
        String colorMagenta = "\u001B[35m";
        
        // caminhos da ávore:

        game.root = new Node(colorBlue + "\nVocê prefere:\n  1. lutar de noite\n  2. lutar de dia" + colorReset);
        game.root.left = new Node(colorGreen + "\nVocê prefere:\n  1. trabalhar sozinho\n  2. trabalhar em equipe" + colorReset);
        game.root.left.left = new Node(colorYellow + "\nVocê prefere:\n  1. usar gadgets (itens, dispositivos)\n  2. habilidades físicas" + colorReset);
        game.root.left.left.left = new Node(colorMagenta + "Batman" + colorReset);
        game.root.left.left.right = new Node(colorMagenta + "Viúva Negra" + colorReset);

        game.root.left.right = new Node(colorCyan + "\nVocê prefere:\n  1. ter poderes alienígenas\n  2. ser um ser humano" + colorReset);
        game.root.left.right.left = new Node(colorMagenta + "Superman" + colorReset);
        game.root.left.right.right = new Node(colorMagenta + "Capitão América" + colorReset);

        game.root.right = new Node(colorGreen + "\nVocê prefere:\n  1. ter habilidades tecnológicas\n  2. ter poderes sobrenaturais" + colorReset);
        game.root.right.left = new Node(colorYellow + "\nVocê gostaria de usar armadura tecnológica?\n  1. sim\n  2. não" + colorReset);
        game.root.right.left.left = new Node(colorMagenta + "Homem de Ferro" + colorReset);
        game.root.right.left.right = new Node(colorMagenta + "Homem-Aranha" + colorReset);

        game.root.right.right = new Node(colorCyan + "\nVocê prefere:\n  1. magia\n  2. força bruta" + colorReset);
        game.root.right.right.left = new Node(colorBlue + "\nVocê prefere ser:\n  1. mago\n  2. bruxa" + colorReset);
        game.root.right.right.left.left = new Node(colorMagenta + "Doutor Estranho" + colorReset);
        game.root.right.right.left.right = new Node(colorMagenta + "Wanda Maximoff" + colorReset);

        game.root.right.right.right = new Node(colorGreen + "\nVocê prefere:\n  1. força incontrolável\n  2. força divina" + colorReset);
        game.root.right.right.right.left = new Node(colorMagenta + "Hulk" + colorReset);
        game.root.right.right.right.right = new Node(colorMagenta + "Thor" + colorReset);

        // frase inicial / refazer quiz
        do {
            System.out.println(asciiArt);
            System.out.println(colorYellow + "\nBem-vindo(a). Vamos descobrir qual super-herói você seria?\nResponda às perguntas e descubra! :D\n" + colorReset);
			game.play();
            System.out.println(colorMagenta + "Deseja refazer o quiz?\n  1. sim\n  2. não" + colorReset);
        } while (game.scanner.nextLine().equalsIgnoreCase("1")); // lê a resposta do usuário para jogar novamente
    }
}
