
package newalgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class solution {
  public Tree EmpT=new Tree();
  public int EmpNum;  
  public double list[]=new double[30];
  public Employee [] invited = new Employee[30];
  int j=0;

  
public void readfile(String filename)throws IOException{

//read file using buffer scanner and store first line in string str:
BufferedReader br;
    br = new BufferedReader(new FileReader(filename));
String str= br.readLine();
//check to see if str is null; if not contine:
while (str != null){
//the "split" method splits the line into an array -(name: info)- 
//depending on the character you send it, in this case it's ":"
//assuming
String info[] = str.split(":"); 
//since we need some indexes as integers insead of string (eg the parent id)
//we will take each index and parse it to the correct type
int ParentId=Integer.parseInt(info[0]);
String EmployeeName=info[1];
int EmployeeId=Integer.parseInt(info[2]);
double Evaluation=Double.parseDouble(info[3]);

//now we make a new employee object to store all these attributes:
Employee e = new Employee(ParentId, EmployeeName, EmployeeId, Evaluation);

//send the object we just created to the addE method 
addE(e);

str = br.readLine();
}//end of while
br.close();
 
}
  
public void addE(Employee e){ 
System.out.println("-------------------------------------------------------------------------");

//adding the head ( first node ): 
if(EmpT.empty()){
    
if(EmpT.insert("Root",e))  {
e.index=EmpNum++;
System.out.print("Successfully added    " );
e.print();  }
}//when #nodes=0


//when there is already nodes in the tree:

//check if the parent is in the tree, if they are, set the current to it:
//checkId is a method in the tree class that traverses the tree
//and searches for the parent id, which if found will set the current to it
//and return true...false otherwise
else if(EmpT.checkId(e.ParentId))  {
    
//check left child
if(EmpT.insert("LeftChild" , e)){
e.index=EmpNum++;
System.out.print("Successfully added    " );
e.print();  }

//check right child
else if(EmpT.insert("RightChild" , e)){
e.index=EmpNum++;
System.out.print("Successfully added    " );
e.print();  }

}// when #nodes>=1

}

public void DP(){

double max= PartyDP(EmpT.root);
System.out.println("the maximum Evaluation sum possible with Dynamic Programing: "+ max);
BacktrackDP(EmpT.root);
for(int i=0 ; i< j ; i++)
invited[i].print();

}

public double PartyDP(BTNode T){
//when the tree is empty we return 0:
if(T==null)
return 0;

//check if it was computed before, if so return the value:
if(list[T.data.index]!=0)
return list[T.data.index];

//when the node is firstly introduced :
double WithT =T.data.Evaluation;

if(T.left !=null)
{
WithT+=PartyDP(T.left.left);
WithT+=PartyDP(T.left.right);
}

if(T.right !=null)
{
WithT+=PartyDP(T.right.left);
WithT+=PartyDP(T.right.right);
}
double WithOutT = PartyDP(T.right)+ PartyDP(T.left);

list[T.data.index]= Math.max(WithT,WithOutT);

return Math.max(WithT, WithOutT);
  }

public void BacktrackDP(BTNode T){
if(T!=null){
 //when the node is firstly introduced :
double WithT =T.data.Evaluation;

if(T.left !=null)
{
WithT+=PartyDP(T.left.left);
WithT+=PartyDP(T.left.right);
}

if(T.right !=null)
{
WithT+=PartyDP(T.right.left);
WithT+=PartyDP(T.right.right);
}

double WithOutT = PartyDP(T.right)+ PartyDP(T.left);

if(list[T.data.index]==WithT){  
Invite(T);

if(T.left !=null)
{
BacktrackDP(T.left.left);
BacktrackDP(T.left.right);
}

if(T.right !=null)
{
BacktrackDP(T.right.left);
BacktrackDP(T.right.right);
}
}

else if(list[T.data.index]==WithOutT){
    
BacktrackDP(T.left);
BacktrackDP(T.right);

}

}    

}

public void BF(){

double max= PartyBF(EmpT.root);
System.out.println("the maximum Evaluation sum possible with Brute Force: "+ max);
BacktrackBF(EmpT.root);
for(int i=0 ; i< j ; i++)
invited[i].print();

}

public double PartyBF(BTNode T){
//when the tree is empty we return 0:
if(T==null)
return 0;

//when the node is firstly introduced :
double WithT =T.data.Evaluation;

if(T.left !=null)
{
WithT+=PartyBF(T.left.left);
WithT+=PartyBF(T.left.right);
}

if(T.right !=null)
{
WithT+=PartyBF(T.right.left);
WithT+=PartyBF(T.right.right);
}
double WithOutT = PartyBF(T.right)+ PartyBF(T.left);

return Math.max(WithT, WithOutT);
  }

public void BacktrackBF(BTNode T){
if(T!=null){
 //when the node is firstly introduced :
double WithT =T.data.Evaluation;

if(T.left !=null)
{
WithT+=PartyBF(T.left.left);
WithT+=PartyBF(T.left.right);
}

if(T.right !=null)
{
WithT+=PartyBF(T.right.left);
WithT+=PartyBF(T.right.right);
}

double WithOutT = PartyBF(T.right)+ PartyBF(T.left);

if( PartyBF(T)==WithT){  
Invite(T);

if(T.left !=null)
{
BacktrackBF(T.left.left);
BacktrackBF(T.left.right);
}

if(T.right !=null)
{
BacktrackBF(T.right.left);
BacktrackBF(T.right.right);
}
}

else if(PartyBF(T)==WithOutT){
    
BacktrackBF(T.left);
BacktrackBF(T.right);

}

}    

}

 public void Invite (BTNode T)    {
 invited[j++]=T.data;
 }  
   
 
}


//**********************************************BTNODE CLASS**************************************************************
class BTNode {
public Employee data;
public BTNode left, right;
 
public BTNode( Employee data) {

this.data = data;
left=right=null;   
}
}


//**********************************************NODE CLASS**************************************************************

class Node<T> {
public T data;
public Node<T> next;

public Node () {
data = null;
next = null;
}

public Node (T val) {
data = val;
next = null;
}

}


//**********************************************LINKEDSTACK CLASS**************************************************************

 class LinkedStack<T> {
private Node<T> top;

public LinkedStack() {
top = null;
}

public boolean empty(){
return top == null;
}

public boolean full(){
return false;
}

public void push(T e){
Node<T> tmp = new Node<>(e);
tmp.next = top;
top = tmp;
}

public T pop(){
T e = top.data;
top = top.next;
return e;
}
}




//**********************************************TREE CLASS**************************************************************
 class Tree {
   public BTNode root, current;

   public Tree() {
      root = current = null;
   }

   public boolean empty(){
      return root == null;
   }

   public Employee retrieve() {
      return current.data;
   }
   
   public void update(Employee val) {
      current.data = val;
   
   }

   public boolean checkId(int id){
      if(traversePostOrder(root , id))
         return true;
   
      return false;
   }

   public boolean traversePostOrder(BTNode node, int id) {
      if (node != null) 
      {
         if(node.data.EmployeeId==id)
         {
            current=node;
            return true;
         }
        
         if( traversePostOrder(node.left , id))
            return true;
      
         if(traversePostOrder(node.right, id))
            return true;
      }
      return false;
   }

   public boolean insert(String rel, Employee val) {
      switch(rel) {
         case "Root":
            if(!empty()) 
               return false;
            current = root = new BTNode(val);
            return true;
         case "Parent": //This is an impossible case.
            return false;
         case "LeftChild":
            if(current.left != null) 
               return false;
            current.left = new BTNode(val);
            current = current.left;
            return true;
         case "RightChild":
            if(current.right != null) 
               return false;
            current.right = new BTNode(val);
            current = current.right;
            return true;
         default:
            return false;
      }
   }

   public boolean find(String rel){
      switch (rel) {
         case "Root": // Easy case
            current = root;
            return true;
         case "Parent":
            if(current == root) 
               return false;
            current = findparent(current, root);
            return true;
         case "LeftChild":
            if(current.left == null) 
               return false;
            current = current.left;
            return true;
         case "RightChild":
            if(current.right == null) 
               return false;
            current = current.right;
            return true;
         default:
            return false;
      }
   }

   public BTNode findparent(BTNode p, BTNode t) {
   // Stack is used to store the right pointers of nodes
      LinkedStack<BTNode> stack = new LinkedStack<BTNode>();
      BTNode q = t;
      while(q.right != p && q.left != p) {
         if(q.right != null)
            stack.push(q.right);
         if(q.left != null)
            q = q.left;
         else
            q = stack.pop(); // Go right here
      }
      return q;
   }

}

//**********************************************EMPLOYEE CLASS**************************************************************
class Employee {
public  String EmployeeName;
public  double Evaluation;
public  int EmployeeId;
public  int ParentId;
public int index;

public Employee(){
ParentId=0;
EmployeeName=null;
EmployeeId=0;
Evaluation=0.0;
index=0;
}

public Employee(int ParentId, String EmployeeName, int EmployeeId ,double Evaluation ){
this.ParentId=ParentId;
this.EmployeeName=EmployeeName;
this.EmployeeId=EmployeeId;
this.Evaluation=Evaluation;
}

public void print(){
System.out.printf("%-3d  %-10s \n",EmployeeId,EmployeeName);
}

} 




