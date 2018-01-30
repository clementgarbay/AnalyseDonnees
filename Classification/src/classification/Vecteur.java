package classification;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class Vecteur {
	public int length;
	private double[] value;
	private static int count=0;
	private int id;
	
	public Vecteur(double...vals) {
		id=++count;
		length=vals.length;
		value = new double[length];
		for(int i=0;i<length;i++)
			value[i] = vals[i];
	}
	
	public Vecteur(int i){
		id=++count;
		length = i;
		value = new double[i];
	}
	
	public Vecteur(int[] vals) {
		id=++count;
		length=vals.length;
		value = new double[length];
		for(int i=0;i<length;i++)
			value[i] = vals[i];
	}

	public double get(int i){
		if(i>length) throw new IllegalArgumentException("Le vecteur est de taille "+length+"(<"+(i+1)+")");
		return value[i];
	}

	public static double distanceCarre(Vecteur u, Vecteur v) {
        return IntStream.range(0, u.length).mapToDouble(i -> Math.pow(v.get(i) - u.get(i), 2)).sum();
	}

	public Vecteur plus(Vecteur v){
		if(length!=v.length) throw new IllegalArgumentException("Les deux vecteurs doivent avoir la m�me dimension.");
		for(int i=0;i<length;i++){
			value[i] = value[i]+v.get(i);
		}
		return this;
	}

	public Vecteur moins(Vecteur v){
		if(length!=v.length) throw new IllegalArgumentException("Les deux vecteurs doivent avoir la m�me dimension.");
		for(int i=0;i<length;i++){
			value[i] = value[i]-v.get(i);
		}
		return this;
	}

	public Vecteur div(double v){
		for(int i=0;i<length;i++){
			value[i] = value[i]/v;
		}
		return this;
	}
	
	public Vecteur mult(double v){
		for(int i=0;i<length;i++){
			value[i] = value[i]*v;
		}
		return this;
	}	
	
	public static Vecteur plus(Vecteur u, Vecteur v){
		if(u.length!=v.length) throw new IllegalArgumentException("Les deux vecteurs doivent avoir la m�me dimension.");
		double[] tmp = new double[u.length];
		for(int i=0;i<u.length;i++){
			tmp[i] = u.get(i)+v.get(i);
		}
		return new Vecteur(tmp);
	}	

	public static Vecteur moins(Vecteur u, Vecteur v){
		if(u.length!=v.length) throw new IllegalArgumentException("Les deux vecteurs doivent avoir la m�me dimension.");
		double[] tmp = new double[u.length];
		for(int i=0;i<u.length;i++){
			tmp[i] = u.get(i)-v.get(i);
		}
		return new Vecteur(tmp);
	}
	
	public static Vecteur mult(Vecteur u, double v){
		double[] tmp = new double[u.length];
		for(int i=0;i<u.length;i++){
			tmp[i] = u.get(i)*v;
		}
		return new Vecteur(tmp);
	}
	
	public static Vecteur div(Vecteur u, double v){
		double[] tmp = new double[u.length];
		for(int i=0;i<u.length;i++){
			tmp[i] = u.get(i)/v;
		}
		return new Vecteur(tmp);
	}	
	
	
	public static double distance(Vecteur u, Vecteur v){
		return Math.sqrt(distanceCarre(u, v));
	}
	
	public boolean equals(Vecteur v){
		if(v==null) return false;
		if(length!=v.length) return false;
		for(int i=0;i<length;i++){
			if(get(i)!=v.get(i)) return false;
		}
		return true;
	}
	
	public static boolean equals(Vecteur u, Vecteur v){
		return u.equals(v);
	}

	public String toString(){
		String res = "( ";
		for(int i=0;i<length;i++){
			res+=(double)((int)(get(i)*100))/100.0;
			if(i==length-1) res+=" )";
			else res+=" , ";
		}
		return res;
	}
	
	public double[] toDouble(){ return this.value; }

    public static Vecteur barycentre(Vecteur[] vecteurs) {
	    int length = vecteurs[0].length;

        return new Vecteur(
            IntStream.range(0, length)
                .mapToDouble(i -> Arrays.stream(vecteurs).mapToDouble(vecteur -> vecteur.get(i)).sum() / vecteurs.length)
                .toArray()
        );
    }
}
