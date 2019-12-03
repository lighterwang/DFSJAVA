import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DFS {
    private int time ;
    public void DFS(Graph graph){
        Vertex[] V = graph.getVertex();
        for(Vertex u:V){
            u.setC("white");
            u.setP(null);
        }
        time=0;
        for(Vertex u:V){
            if(u.getC().equals("white")){
                DFS_visit(graph,u);
            }
        }
    }
    private void DFS_visit(Graph G,Vertex u){
        System.out.print(u.getId()+" ");
        u.setDt(++time);
        u.setC("gray");
        List<Edge> edges = G.getEdges(u.getId());
        for(Edge edge:edges){
            Vertex v = G.getVertex(edge.getEnd());
            if(v.getC().equals("white")) {
                v.setP(u);
                DFS_visit(G, v);
            }
        }
        u.setC("black");
        u.setFt(++time);
    }
}

//ͼ��
class Edge{
    private int start;
    private int end;
    private int value;
    Edge nextEdge;

    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Edge() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Edge getNextEdge() {
        return nextEdge;
    }

    public void setNextEdge(Edge nextEdge) {
        this.nextEdge = nextEdge;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

//ͼ���
class Vertex{
    private int id; //���
    private String c; //write,gray,black
    private int d;  //����
    private Vertex p;
    private int dt; //ʱ���
    private int ft; //ʱ���
    Edge first = new Edge();



    public Edge getFirst() {
        return first;
    }

    public void setFirst(Edge first) {
        this.first = first;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getFt() {
        return ft;
    }

    public void setFt(int ft) {
        this.ft = ft;
    }
    public Vertex(String c, int d, Vertex p) {
        this.c = c;
        this.d = d;
        this.p = p;
    }

    public Vertex(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public Vertex getP() {
        return p;
    }

    public void setP(Vertex p) {
        this.p = p;
    }
}

//����ͼ�ṹ
class Graph {
    //����һ�µ����Ա���
    Vertex[] u;//���嶥������
    int e; //����
    int v; //������

    //ͼ��Ĺ��캯��
    public Graph(int v,int e) {
        this.v = v;
        this.e =e;
        u = new Vertex[v];
        for(int i=0;i<v;i++){
            u[i] =new Vertex(i);
        }
    }


    //���ر�����
    public List<Edge> getEdges(int u){
        List<Edge> edges = new LinkedList<>();
        Vertex vertex = this.u[u];
        Edge edge = vertex.getFirst();
        edges.add(vertex.getFirst());
        while(edge.getNextEdge()!=null) {
            edge=edge.getNextEdge();
            edges.add(edge);
        }
        return edges;
    }

    //����vertex��set get����
    public Vertex[] getVertex() {
        return u;
    }

    public Vertex getVertex(int id){
        return u[id];
    }




    //---����������
    public static void main(String[] args) throws FileNotFoundException {
        CreateGraph.createGraph();
        Graph graph = CreateGraph.getGraph();
        CreateGraph.outputGraph();
        System.out.println("DFS���������");
        new DFS().DFS(graph);
    }
}

class CreateGraph {
    private static Graph G;
    public static Graph getGraph(){
        return G;
    }
    public static void createGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.println("�����붥����v�ͱ���e:");
        int v = sc.nextInt();
        int e = sc.nextInt();
        G = new Graph(v, e);
        for (int i = 0; i < G.v; i++) {
            G.u[i].first = null; // ���ɻ�ȱ
        }
        System.out.println("�����������Ϣ(�ÿո����):");
        for (int i = 0; i < G.e; i++) {
            Edge en1 = new Edge();
            // ��֤e1,e2���ǺϷ�����
            int e1 = sc.nextInt();
            int e2 = sc.nextInt();
            en1.setStart(e1);
            en1.setEnd(e2);
            en1.setValue(1);
            if(G.u[e1].first==null){
                G.u[e1].first = en1;
            }else{
                Edge edge = G.u[e1].first;
                while(edge.nextEdge!=null)
                    edge =edge.nextEdge;
                edge.nextEdge =en1;
            }

            Edge en2 = new Edge();
            en2.setStart(e2);
            en2.setEnd(e1);
            en2.setValue(1);
            if(G.u[e2].first==null){
                G.u[e2].first = en2;
            }else{
                Edge edge = G.u[e2].first;
                while(edge.nextEdge!=null)
                    edge =edge.nextEdge;
                edge.nextEdge =en2;
            }
        }
    }
    public static void outputGraph() {
            System.out.println("����ڽӱ�洢�����");
            for (int i = 0; i < G.v; i++) {
                System.out.print(i);
                Edge en = G.u[i].first;
                while (en != null) {
                    //System.out.println("Edge:"+en.getStart()+" "+en.getEnd());
                    System.out.print("->" + en.getEnd());
                    en = en.nextEdge;
                }
                System.out.println();
            }
    }
}

