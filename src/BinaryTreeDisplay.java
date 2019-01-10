import java.awt.Container;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;

class Node{
	Node left;
	Node right;
	int value;
	public Node(int v) {
		value=v;
	}
}

class Tree{
	Node root;
}

public class BinaryTreeDisplay extends JComponent{
	private static final long serialVersionUID = 1L;
	public static final int a=20;
	public static Tree tree=new Tree();
	
	public static void main(String[] args) {
		Node n1=new Node(1);
		Node n2=new Node(2);
		Node n3=new Node(3);
		Node n4=new Node(4);
		Node n5=new Node(5);
		Node n6=new Node(6);
		Node n7=new Node(7);
		n1.left=n2;
		n1.right=n2;
		n2.left=n4;
		n2.right=n3;
		n4.left=n5;
		n4.right=n6;
		n3.left=n7;
		n3.right=n6;
		tree.root=n1;
		
		JFrame jf = new JFrame();
		jf.setBounds(200,200,300,200);
		jf.setVisible(true);
		jf.add(new BinaryTreeDisplay());
		jf.setDefaultCloseOperation(jf.DISPOSE_ON_CLOSE);
	}
	
	public LinkedList<Integer> getStartPosition(){
		LinkedList<Integer> pos=new LinkedList<Integer>();
		pos.add(1);
		LinkedList<Node> queue=new LinkedList<Node>();
		queue.add(tree.root);
		LinkedList<Node> queue1=new LinkedList<Node>();
		int maxSize=1;
		while(!queue.isEmpty()) {
			Node n=queue.remove();
			if(n.left!=null)
				queue1.add(n.left);
			if(n.right!=null)
				queue1.add(n.right);
			if(queue.isEmpty()) {
				queue=queue1;
				queue1=new LinkedList<Node>();
				pos.add(queue.size());
				if(queue.size()>maxSize)
					maxSize=queue.size();
			}
		}
		for(int i=0;i<pos.size();i++) {
			pos.set(i, (maxSize-pos.get(i))/2);
		}
		
		return pos;
	}
	
	public void paint(Graphics g) {
		LinkedList<Node> queue=new LinkedList<Node>();
		LinkedList<Node> queue1=new LinkedList<Node>();
		LinkedList<Integer> pos=getStartPosition();

		queue.add(tree.root);
		int line=0;
		int position=pos.get(line);
		
		
		while(!queue.isEmpty()) {
			Node n=queue.remove();
			paintSpecificRectangle(g, position, line, n.value);
			if(n.left!=null) {
				queue1.add(n.left);
				connectRectangle(g, position, line,  pos.get(line+1)+queue1.size()-1, line+1);
			}
			if(n.right!=null) {
				queue1.add(n.right);
				connectRectangle(g, position, line, pos.get(line+1)+queue1.size()-1, line+1);
			}
			position++;
			if(queue.isEmpty()) {
				line++;
				position=pos.get(line);
				queue=queue1;
				queue1=new LinkedList<Node>();
			}
		}
	}
	public void paintSpecificRectangle(Graphics g,int position,int line,int value) {
		g.drawRect(position*2*a, line*2*a, a, a);
		g.drawString(""+value, position*2*a+a/2, line*2*a+a/2);
	}
	
	public void connectRectangle(Graphics g,int position1,int line1,int position2,int line2) {
		g.drawLine(position1*2*a+a/2, line1*2*a+a, position2*2*a+a/2, line2*2*a);
	}
}
