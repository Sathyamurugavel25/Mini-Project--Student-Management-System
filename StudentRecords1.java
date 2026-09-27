
import java.util.*;
public class StudentRecords1 {
    String name;
    String add;
    String ph;
    int m1,m2,m3,tot;
    double avg;
    char grade;
    static int startingPt=100;
    public static void insert(int n, Scanner sc, HashMap<Integer, StudentRecords1> map){
        
        for(int i=0;i<n;i++){
             StudentRecords1 std1=new StudentRecords1();
            System.out.println("Student no "+(i+1)+" : ");
            std1.name=sc.nextLine();
            std1.add=sc.nextLine();
            std1.ph=sc.nextLine();
            std1.m1=sc.nextInt();
            std1.m2=sc.nextInt();
            std1.m3=sc.nextInt();
            sc.nextLine();
            std1.tot=(std1.m1+std1.m2+std1.m3);
            std1.avg=((std1.tot)/3.0);
            if(std1.avg>=90){
                std1.grade='O';
            }
            else if(std1.avg>=80){
                 std1.grade='A';
            }
            else if(std1.avg>=70){
                 std1.grade='B';
            }
            else if(std1.avg>=60){
                std1.grade='C';
             }
            else
                 std1.grade='F';

            map.put(++startingPt,std1);          
         }
    }
    public static void search(int rollno,HashMap<Integer, StudentRecords1> map){
       StudentRecords1 rec= map.get(rollno);
       if(rec!=null)
       {
        System.out.println(rec.name +" "+ rec.add+" "+rec.ph+" "+rec.m1+" "+rec.m2+" "+rec.m3+" "+rec.tot+" "+rec.avg+" "+rec.grade);           
       }
       else
        System.out.println("rec not found");       
    }
    public static void remove(int rollno,HashMap<Integer, StudentRecords1> map){
         map.remove(rollno);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        HashMap<Integer,StudentRecords1> map=new HashMap<>();
        boolean flag=true;

         while(flag){
           
            System.out.println("Enter a no that represent the operation that you want to perform: ");
            int choices=sc.nextInt();
            switch(choices){
                case 1:{
                    System.out.println("you have chosen to insert the elements :");
                    System.out.println("Enter tot no of students: ");
                    int n=sc.nextInt();
                    sc.nextLine();
                    insert(n,sc,map);
                    
                    break;
                }
                case 2:{
                    System.out.println("You have chosen to search the rollno to see the details: ");
                    System.out.println("Enter the rollno to see the details:");
                    int rollno=sc.nextInt();
                    search(rollno,map);
                    break;
                 }
                case 3:{
                    System.out.println("You have chosen to delete values in the records: ");
                    System.out.println("Enter the rollno which u want to delete:");
                    int rollno=sc.nextInt();
                    remove(rollno,map);
                    break;
                }
                case 4:{
                    System.out.println("you have chosen to exit :"); 
                    flag=false;  
                    break;
                       
                }
            }
        }
    }
}
