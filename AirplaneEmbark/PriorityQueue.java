package AirplaneEmbark;

import java.util.*;
import java.io.*;

/**
 * Clasa implementarea unei cozi de priorități printr-un maxheap.
 * În cadrul acestei clase este implementată metoda main.
 * @author Patricia POPA-MIHAI
 */

public class PriorityQueue {
    private Node root;
    private int nodesNumber = 0;

    PriorityQueue() {
        
    }

    private ArrayList<Integer> toBinary(int n) {
        ArrayList<Integer> v = new ArrayList<Integer>();

        while(n != 0) {
            int r = n % 2;
            v.add(r);
            n = n / 2;
        }
        return v;
    }

    private Node returnParent(int position) {
        Node current = root;
        ArrayList<Integer> v = toBinary(position/2);
    
        for(int i = v.size() - 2; i >= 0; i--) {
            if(v.get(i) == 0) current = current.left;  
            else current = current.right;
        }
        return current;
    }

    private Node returnCurrent(int position) {
        Node current = root;
        ArrayList<Integer> v = toBinary(position);
    
        for(int i = v.size() - 2; i >= 0; i--) {
            if(v.get(i) == 0) current = current.left;  
            else current = current.right;
        }
        return current;
    }

    private void maxHeapify(Node node) {
        Node largest = node;
        Node left = null, right = null;
        if(node.left != null) left = node.left;
        if(node.right != null) right = node.right;

        if(left != null && left.getPriority() > node.getPriority())
            largest = left;
        if(right != null && right.getPriority() > largest.getPriority())
            largest = right;

        if(largest != node) {
            Node swapNode = new Node();
            swapNode.setParam(largest.getData(), largest.getPriority());
            largest.setParam(node.getData(), node.getPriority());
            node.setParam(swapNode.getData(), swapNode.getPriority());
            maxHeapify(largest);
        }
    }

    private void preorder(Node node) {
        if(node != null) {
            System.out.print(node.getID() + ' ');
            preorder(node.left);
            preorder(node.right);
        }
    }

    private Node searchNode(Passenger p) {
        Node current = null;
        for(int i = 0; i < nodesNumber; i++) {
            current = returnCurrent(i);
            if(current.getID().equals(p.getID())) {
                return current;
            }
        }
        return current;
    }

    /**
     * Metoda insert(Passenger p, int priority) introduce în coada de
     * prioritate pasagerul p cu prioritatea priority și rearanjează coada în
     * ordinea priorităților.
     * @param p pasagerul (familie, singur sau grup) care este adăugat în coadă
     * @param priority prioritatea pasagerului
     */
    public void insert(Passenger p, int priority) {
        Node tempNode = new Node(p, priority);
        Node current;

        if(root == null) {
            root = tempNode;
            nodesNumber++;
        }
        else {
            current = root;
            ArrayList<Integer> v = toBinary(nodesNumber + 1);

            for(int i = v.size() - 2; i > 0; i--) {
                if(v.get(i) == 0)current = current.left;
                else current = current.right;
            }

            if(v.get(0) == 0) {
                current.left = tempNode;
                current = current.left;
            }
            else {
                current.right = tempNode;
                current = current.right;
            }

            nodesNumber++;
            int i = nodesNumber;
            while(i > 0) {
                current = returnCurrent(i);
                maxHeapify(current);
                i--;
            }            
        }
    }

    /**
     * Metoda embark() introduce pasagerii în avion și îi scoate din coada
     * de priorități. În urma acestei operații, ultimul pasager adăugat în
     * coadă devine root (primul în coadă), iar coada se rearanjează după
     * priorități.
     */
    public void embark() {
        Node current = root;

        if(root == null) return;

        ArrayList<Integer> v = toBinary(nodesNumber);

        for(int i = v.size() - 2; i >= 0; i--) {
            if(v.get(i) == 0) current = current.left;
            else current = current.right;
        }

        if(current == root) {
            nodesNumber = 0;
            root = null;
            return;
        }

        Node parent = returnParent(nodesNumber);
        current.swapRoot(root, parent, nodesNumber);
        nodesNumber--;
        
        int i = nodesNumber;
        while(i > 0) {
            current = returnCurrent(i);
            maxHeapify(current);
            i--;
        }
    }

    /**
     * Metoda list() afișează în preordine pasagerii ce urmează
     * să fie îmbarcați în avion, în funcție de prioritate.
     */
    public void list() {
        preorder(root);
    }

    /**
     * Metoda delete(Passenger p) poate șterge fie o persoană dintr-un
     * sau familie sau un pasager (reprezentând o familie, un grup sau
     * sau un pasager singur) din coda de priorități
     */
    public void delete(Passenger p) {
        Node current = searchNode(p);

        if(p.isPassengerEmpty()) {
            Node parent = returnParent(nodesNumber);
            Node lastNode = returnCurrent(nodesNumber);
            lastNode.swapRoot(current, parent, nodesNumber);
            nodesNumber--;

            int i = nodesNumber;
            while(i >= 0) {
                current = returnCurrent(i);
                maxHeapify(current);
                i--;
            }
        }
        else {
            current.getData().removePerson(p.getFirstPersonName());
            current.setPriority(current.getData().getPriority());
            int i = nodesNumber;
            while(i >= 0) {
                current = returnCurrent(i);
                maxHeapify(current);
                i--;
            }
        }
    }

    /**
     * Metoda main primește ca argumente fișere de intrare, ieșire și
     * un fișier temporar. În cadrul metodei citește din fișierul de
     * intrare, salvează pasagerii într-o instanță a clasei Plane și
     * realizează operațiile scrise în fișierul de intrare. Outuputul
     * de la list esre salvat într-un fișier temporar care este editat
     * ulterior (este șters ultimul spațiu de la finalul fiecărei linii
     * și utlimul endline).
     * @param argv fișierele input, temp și output
     * @throws IOException fișierul nu a fost găsit
     */
    public static void main(String[] argv) throws IOException {
        PriorityQueue queue = new PriorityQueue();

        //File inputFile = new File(argv[0]);
        //File outputFile = new File(argv[1]);
        //File tempFile = new File(argv[2]);

        File inputFile = new File("C:/Users/patri/Documents/Poli/an_ii_sem_i/POO/Teme/Tema_1/in_files/queue.in");
        File outputFile = new File("C:/Users/patri/Documents/Poli/an_ii_sem_i/POO/Teme/Tema_1/out_files/queue.out");
        File tempFile = new File("C:/Users/patri/Documents/Poli/an_ii_sem_i/POO/Teme/Tema_1/temp_files/queue.temp");

        Scanner input = new Scanner(inputFile);
        PrintStream output = new PrintStream(tempFile);
        System.setOut(output);
        
        int passenger_number = input.nextInt();
        Plane plane = new Plane(passenger_number);
        for(int i = 0; i < passenger_number; i++) {
            String id = input.next(); 
            String name = input.next();
            int age = input.nextInt();
            Ticket ticket = new Ticket(input.next(), input.nextBoolean());
            boolean specialNeeds = input.nextBoolean();

            Passenger passenger;    
            if(!plane.searchPlane(id)) {
                switch(id.charAt(0)) {
                    case 's':
                        passenger = new Alone(id, ticket, name, age, specialNeeds);
                        plane.addPeople(passenger);
                        break;
                    case 'f':
                        passenger = new Family(id, ticket);
                        passenger.addPerson(name, age, specialNeeds);
                        plane.addPeople(passenger);
                        break;
                    case 'g':
                        passenger = new Group(id, ticket);
                        passenger.addPerson(name, age, specialNeeds);
                        plane.addPeople(passenger);
                        break;
                }
            }
            else {
                passenger = plane.getPassenger(id);
                passenger.addPerson(name, age, specialNeeds);
            }
        }
        
        String line = input.nextLine();
        while(input.hasNext()) {
            line = input.nextLine();
            if(line.charAt(0) == 'i') {
                String[] param = line.split(" ");
                Passenger passenger = plane.getPassenger(param[1]);
                queue.insert(passenger, passenger.getPriority());
            }
            if(line.charAt(0) == 'e') {
                queue.embark();
            }
            if(line.charAt(0) == 'l') {
                queue.list();
                System.out.print('\n');
            }
            if(line.charAt(0) == 'd') {
                if(line.length() == 9) {
                    String[] param = line.split(" ");
                    Ticket ticket = new Ticket("e", false);
                    Passenger passenger = new Group(param[1], ticket);
                    queue.delete(passenger);
                }
                else {
                    String[] param = line.split(" ");
                    Ticket ticket = new Ticket("e", false);
                    Passenger passenger = new Group(param[1], ticket);
                    passenger.addPerson(param[2], 1, false);
                    queue.delete(passenger);
                }
            }
        }

        input.close();
        output.close();

        //CODE TO CREATE OUTPUT FILE
        output = new PrintStream(outputFile);
        System.setOut(output);

        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        BufferedReader reader2 = new BufferedReader(new FileReader(tempFile));
        String line2 = reader2.readLine();
        line = "";
        while(line != null) {
            line = reader.readLine();
            line2 = reader2.readLine();
            if(line2 != null &&line != null && line.length() > 0) {
                line = line.substring(0, line.length() - 1) + '\r' + '\n';
                System.out.print(line);
            }
            if(line2 == null && line != null) {
                line = line.substring(0, line.length() - 1);
                System.out.print(line);
            }
        }

        reader.close();
        reader2.close();
        output.close();
    }
}