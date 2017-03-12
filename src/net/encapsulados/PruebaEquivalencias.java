package net.encapsulados;


import javax.swing.*; 

public class PruebaEquivalencias {
	
	AFD2 a = new AFD2();
	AFD2 e = new AFD2();
	
	//Captura
	String auxK1 = "q0,q1,q2";
	String auxK2 = "r0,r1";
	String auxE = "a,b";
	String auxF1 = "q0";
	String auxF2 = "r0";
	String auxS1 = "q0";
	String auxS2 = "r0";
	int alpha=0;
	int k1=0;
	int k2=0;
	int F1=0;
	int F2=0;
	SepararStrings sx = new SepararStrings();
	int tem1=0, tem2=0, M1 = 10, M2 = 10;
	String Trans[][] = new String [M1][M2];//transiciones
	String Equivalentes ="";
	int n=0, m=0, actual=0;//Apuntadores donde n es el numero de pareja de transiciones, m es la transicion para M1 y M2 para los estados
	//y actual es el par para n de comparaciones actualmente; sugerencia utilizar arreglo dinamico 
	String TransM1="", TransM2="";
	int tope=0;
	
	
	public void InputAFD(){
		alpha=sx.contar(auxE);
		a.setE(sx.Extraer(alpha, auxE));
		for (int x=0; x<alpha; x++) {
		//System.out.println("Elemetos de sigma "+a.getE()[x]);	
		}
		k1=sx.contar(auxK1);
		a.setK(sx.Extraer(k1, auxK1));
		for (int x=0; x<k1; x++) {
		//JOptionPane.showMessageDialog(null,"Estados Aut1 "+a.getK()[x]);	
		}
		k2=sx.contar(auxK2);
		e.setK(sx.Extraer(k2, auxK2));
		for (int x=0; x<k2; x++) {
		//JOptionPane.showMessageDialog(null,"Estados Aut2 "+e.getK()[x]);	
		}
		F1=sx.contar(auxF1);
		a.setF(sx.Extraer(F1, auxF1));
		for (int x=0; x<F1; x++) {
		//JOptionPane.showMessageDialog(null,"Estados Finales1 "+a.getF()[x]);	
		}
		F2=sx.contar(auxF2);
		e.setF(sx.Extraer(F2, auxF2));
		for (int x=0; x<F2; x++) {
		//JOptionPane.showMessageDialog(null,"Estados Finales2 "+e.getF()[x]);	
		}
		a.setS(auxS1);
		//JOptionPane.showMessageDialog(null,"Estado inicial1 "+a.getS());	
		e.setS(auxS2);
		//JOptionPane.showMessageDialog(null,"Estado inicial2 "+e.getS());
				
	}
	
	public void LlenarDelta() {
		String ad [][]= new String [(k1)][(alpha)];//+1+1
		String ad2 [][]=new String [k2][alpha];
		//System.out.println(k1+" "+alpha);
		for(int x=0; x<k1; x++){
			for(int y=0; y<alpha; y++){
				ad[x][y]=JOptionPane.showInputDialog("Por "+a.getK()[x]+" con "+a.getE()[y]+ " Dime hacia que estado va:");
				a.setDelta(ad);
			}
		}
		for(int x=0; x<k2; x++){
			for(int y=0; y<alpha; y++){
				ad2[x][y]=JOptionPane.showInputDialog("Por "+e.getK()[x]+" con "+a.getE()[y]+ " Dime hacia que estado va:");
				e.setDelta(ad2);
			}
		}	
	}
	
	public void ComparacionAFD (){// en este metodo se comprueban que sean finales o no finales los estados de momento funciona solo con los iniciales

		int sonfinales1=1;
		int sonfinales2=1;
		for(int x=0; x<F1; x++){
			if(a.getS().equals(a.getF()[x])){
				sonfinales1=2;
				break;
			}
		}
		for(int x=0; x<F2; x++){
			if(e.getS().equals(e.getF()[x])){
				sonfinales2=2;
				break;
			}
		}
			if(sonfinales1==sonfinales2){
				
				//System.out.println("ambos son finales"+sonfinales1+sonfinales2);
			}else{
				//System.out.println("no son finales"+sonfinales1+sonfinales2);
			}
			//if(a.getS()!=a.getF()[x]&&e.getS()!=e.getF()[x]){
				//continua comparando se va al paso 2
			//}else{
				//ya no compara no son equivalentes
			//}
	}
	
	public void InsertarTrans (){// en este metodo se insertan las transiciones para los estados iniciales
		for(int x=0; x<k1; x++){//buscando inicial en estados K1
			if(a.getS().equals(a.getK()[x])){
				Trans[n][m]=a.getK()[x];
				m++;
			}
		}
		for(int x=0; x<k2; x++){//buscando inicial para estados K2
			if(e.getS().equals(e.getK()[x])){
				Trans[n][m]=e.getK()[x];
			}
		}
		m=0;
		tope++;
		n++;
	}
	
	public void ObtenerTransIni (){
		
			int ini1=0;
			int ini2=0;
			int j=0;	
			int k=0;
			for(int x=0; x<k1; x++){
				if(Trans[ini1][ini2].equals(a.getK()[x])){//busca posicion en k para automata a
					j=x;
				}	
			}
			ini2++;
			for(int x=0; x<k2; x++){
				if(Trans[ini1][ini2].equals(e.getK()[x])){//busca posicion en Ke para automata 2
					k=x;
				}
			}
			for(int y=0; y<alpha; y++){//obteniendo estado en trans
				TransM1=a.getDelta()[j][y];
				System.out.println("Para insertar a M1: "+TransM1);
				TransM2=e.getDelta()[k][y];
				System.out.println("Para insertar a M2: "+TransM2);
				BusquedaInsertar();
			}
	}
	
	public void BusquedaInsertar(){//Busqueda para insertar 
		boolean insertar = true;
		for(int x=0; x<tope; x++){//Trans.length
			for(int y=0; y<(alpha+1); y++){
				while(TransM1.equals(Trans[x][y])!=TransM2.equals(Trans[x][y+1])){//mientras los estados sean diferentes a los que estan en la tabla insertar
					//no se inserta
					//Falta verificar que no sea final
					insertar = false;
					break;
				}
			}
		}
		if(insertar = true){
			Trans[n][m]=TransM1;
			m++;
			Trans[n][m]=TransM2;
		}
		n++;//aumenta el numero de transiciones en 1
		m=0;//vuelve al estado inicial en el alpha para insertar
		//imprimir tabala de estados
		for(int x=0; x<n; x++){
			for(int y=0; y<alpha; y++){
				System.out.println("Tabla: "+Trans[x][y]);
			}
		}
	}
	
	public static void main (String args[]){
		PruebaEquivalencias x = new PruebaEquivalencias ();
		x.InputAFD();
		x.LlenarDelta();
		x.ComparacionAFD();
		x.InsertarTrans();
		x.ObtenerTransIni();
	}

}
