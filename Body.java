import java.lang. *;

public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	//constructor
	public Body(double xP, double yP, double xV, double yV,double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}
	//calcDistance
	public double calcDistance(Body b1){
		double distance;
		distance = Math.sqrt(Math.pow(b1.xxPos - this.xxPos, 2) + Math.pow(b1.yyPos - this.yyPos, 2));
		return distance;

	}
	//calcForceExertedBy
	public double calcForceExertedBy(Body b1){
		double G = 6.67e-11;
		double force;
		force = G * this.mass * b1.mass/Math.pow(calcDistance(b1),2);
		return force;
	}
	//calcForceExertedByX and calcForceExertedByY
	public double calcForceExertedByX(Body b1){
		double total_force = this.calcForceExertedBy(b1);
		double force_x = total_force * (b1.xxPos - this.xxPos)/this.calcDistance(b1);
		return force_x;


	}

	public double calcForceExertedByY(Body b1){
		double total_force = this.calcForceExertedBy(b1);
		double force_y = total_force * (b1.yyPos - this.yyPos)/this.calcDistance(b1);
		return force_y;
		
	}
	//calcNetForceByX and calcNetForceByX
	public boolean equals(Body b1){
		return this == b1;
	}
	public double calcNetForceExertedByX(Body[] b){
		double force = 0;
		for(int i = 0; i < b.length; i++){
			if(this.equals(b[i])){
				continue;
			}
			force += this.calcForceExertedByX(b[i]);
		}
		return force;
	}

	public double calcNetForceExertedByY(Body[] b){
		double force = 0;
		for(int i = 0; i < b.length; i++){
			if(this.equals(b[i])){
				continue;
			}
			force += this.calcForceExertedByY(b[i]);
		}
		return force;
	}
	//update
	public void update(double dt,double fX,double fY){
		double a_X = fX / this.mass;
		double a_Y = fY / this.mass;
		this.xxVel = this.xxVel + dt * a_X;
		this.yyVel = this.yyVel + dt * a_Y;
		this.xxPos = this.xxPos + this.xxVel* dt;
		this.yyPos = this.yyPos + this.yyVel * dt;

	}

	//draw
	public void draw(){
		String img = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, img);

	}




}