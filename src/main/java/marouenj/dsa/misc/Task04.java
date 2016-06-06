/*
 * Time limit : 2sec / Stack limit : 256MB / Memory limit : 256MB
 * 
 * Question:
 * There are N students in a classroom. You will be given N relationships between them.
 * Each student has a student number ranging from 1 to N, and the student whose student number is i is called i.
 * In this classroom, they have a routine they use to pass on information utilizing their contact network.
 * They will pass on the information following the rules shown below:
 * 
 * First, Student 1 will receive the information. At this stage, no one except for Student 1 knows about this information.
 * The person who received the information will choose one person who has not received it yet with equal probability then deliver the info to them.
 * This process ends when all of your friends learned about the info.
 * Output the expected value of the number of people to deliver the information.
 * Errors are acceptable when the value of absolute errors or relative errors are no more than 10−6.
 * 
 * Input
 * Input will be given in the following format from Standard Input:
 * 
 * N
 * c11 c12 c13 … c1N
 * c21 c22 c23 … c2N
 * :
 * cN1 cN2 cN3 … cNN
 * 
 * On the first string, integer that indicates the number of people in the classroom, N(1≦N≦14), will be given.
 * From the second string to the N string, friendships will be given.
 * cij indicates the following about integers i and j:
 * If cij is Y, the student i and the student j are friends.
 * If cij is N, the student i and the student j are not friends.
 * 
 * Output
 * Output the expected value of the number of people to deliver the information.
 * Errors are acceptable when the value of absolute errors or relative errors are no more than 10−6.
 * 
 * Also, make sure to insert a line break at the end of the output.
 * 
 * Input Example # 1
 * 4
 * NYYY
 * YNNN
 * YNNY
 * YNYN
 * Output Example #1
 * 2.66666666666666666
 * 
 * The number of people who received the information When Student 1 delivers the information to Student 2 is 2 people.
 * When Student 3 delivers the information to Student 4, the number of people who received the information is 3 people.
 * 
 * Input Example #2
 * 5
 * NNNNN
 * NNYYY
 * NYNYY
 * NYYNY
 * NYYYN
 * Output Example #2
 * 1
 * 
 * Since Student 1 has no friend, the information will never reach anyone except for Student 1.
 * 
 * Input Example #3
 * 10
 * NNNNYNYNNY
 * NNYYNNYNYY
 * NYNYYYNYYN
 * NYYNYYYNYY
 * YNYYNYYYNN
 * NNYYYNYYYN
 * YYNYYYNYYN
 * NNYNYYYNYN
 * NYYYNYYYNY
 * YYNYNNNNYN
 * Output Example# 3
 * 9.5018896919296
 * 
 */

package marouenj.dsa.misc;

import marouenj.dsa.reuse.Arrays;

import java.util.Scanner;

public class Task04 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLine();
        }
        sc.close();

        Integer[] visited = new Integer[n];
        for (int i = 0; i < n; i++) {
            visited[i] = 0;
        }

        System.out.println(avg(0, arr, visited));
    }

    public static double avg(int student_id, String[] connections, Integer[] visited) {
        int denom = 0;
        double total = 0;
        for (int i = 0; i < connections[0].length(); i++) {
            if (i != student_id && connections[student_id].charAt(i) == 'Y' && visited[i] != 1) {
                denom++;
                Integer[] visited_clone = Arrays.clone(visited);
                visited_clone[student_id] = 1;
                total += avg(i, connections, visited_clone);
            }
        }
        if (denom == 0)
            return 1;
        return 1 + (total / denom);
    }
}
