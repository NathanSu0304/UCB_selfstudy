import java.util.Scanner;

public class NBody{
	public static double readRadius(String dir){
		In in = new In(dir);
		int Number_data = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String dir){
		In in = new In(dir);
		int size = in.readInt();
		double radius = in.readDouble();
		Body[] b = new Body[size];

		for(int i = 0; i < size; i++){
			b[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
		}
		return b;
	}
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] b = readBodies(filename);


		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		//StdDraw.clear();
		StdDraw.picture(0,0,"./images/starfield.jpg");
		/*for(int i = 0; i < b.length;i++)
		{
			b[i].draw();
		}*/
		for(int i = 0; i < T; i += dt){
			double[] xForces = new double[b.length];
			double[] yForces = new double[b.length];
			
			for(int j = 0; j < b.length; j++)
			{
				b[j].draw();
				xForces[j] = b[j].calcNetForceExertedByX(b);
				yForces[j] = b[j].calcNetForceExertedByY(b);
			}
			
			for(int m = 0; m < b.length; m++)
			{
				b[m].update(dt,xForces[m],yForces[m]);
			}

			StdDraw.picture(0,0,"./images/starfield.jpg");

			for(int n = 0; n < b.length;n++)
			{
				b[n].draw();
			}

			StdDraw.show();
			StdDraw.pause(10);

			
		}

		StdOut.printf("%d\n", b.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < b.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            	b[i].xxPos, b[i].yyPos, b[i].xxVel,
            	b[i].yyVel, b[i].mass, b[i].imgFileName);   
        }




	}

}