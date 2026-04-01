import BasicIO.*;

public class Main {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null; //initialize the next pointer as null
        }
    }

    static class Header {
        int count;
        Node next;

        public Header() {
            this.count = 0;
            this.next = null;
        }
    }

    public static class CodeBook {
        public Header[] codeBook;

        public CodeBook() {
            codeBook = new Header[128];

            for (int i=0; i<codeBook.length; i++) {
                codeBook[i] = new Header();
            }

            for (int i = 0; i < 2000; i++) {
                int aChar = (int) (Math.random() * 128);

                Add(codeBook[aChar], i);
            }
        }

        void Add (Header list, int aCode) {
            Node newNode = new Node(aCode); // Create a new node with the given data
            Node current = list.next;
            Node previous = null;

            while(current != null && current.data < aCode) {
                previous = current;
                current = current.next;
            }

            if (previous == null) { // If the list is empty
                list.next = newNode;
            } else {
                previous.next = newNode;
            }

            newNode.next = current;
            list.count++;
        }

        public Header[] getCodeBook () {
            return codeBook;
        }

        void displayCodeBook() {
            for (int i = 0; i < codeBook.length; i++) {
                Header list = codeBook[i];

                if (list.count > 0) {
                    System.out.print((char) i + " (" + i + "): ");

                    Node current = list.next;
                    while (current != null) {
                        System.out.print(current.data + " -> ");
                        current = current.next;
                    }
                    System.out.println("null");
                }
            }
        }
    }


    static void Encode(ASCIIDataFile input, ASCIIOutputFile output, CodeBook codeBook) {

        //create a loop that goes through every letter in the txt file and adds the position to codebook
        while (!input.isEOF()) {
            int aChar = input.readC();

            Header list = codeBook.getCodeBook()[aChar];
            Node current = list.next;

            int RandInt = (int) (Math.random() * list.count) + 1;

            for (int i = 1; i < RandInt; i++) {
                current = current.next;
            }

            char encryptedChar = (char) current.data;

            output.writeC(encryptedChar);
            output.writeC(' ');

            //System.out.println("Character: " + aChar + " Encrypted Value: " + current.data + " as Character: " + encryptedChar);
        }


    }

    static void Decode (ASCIIDataFile input, ASCIIOutputFile output, CodeBook codeBook){
        while (!input.isEOF()) {
            int encryptedChar = input.readC();
            input.readC();

            for (int i = 0; i < codeBook.codeBook.length; i++) {
                Header list = codeBook.getCodeBook()[i];
                Node current = list.next;

                while (current != null) {
                    if (current.data == encryptedChar) {
                        output.writeC((char) i);
                        break;
                    }
                    current = current.next;
                }
            }
        }

    }

    public static void main(String[] args) {
        CodeBook codebook = new CodeBook();
        ASCIIDataFile input = new ASCIIDataFile();
        ASCIIOutputFile output = new ASCIIOutputFile("encrypted.txt");

        Encode(input, output, codebook);

        ASCIIOutputFile decrypt = new ASCIIOutputFile("decrypted.txt");
        ASCIIDataFile inp = new ASCIIDataFile();

        Decode(inp, decrypt, codebook);
    }

}