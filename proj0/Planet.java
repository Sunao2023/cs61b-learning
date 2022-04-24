public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    /** Initialize a new planet with the given parameters */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** Make a copy of the given planet */
    public Planet(Planet b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    /** Calculate the distance between this planet and another */
    public double calcDistance(Planet Other) {
        double Distance = Math.sqrt(Math.pow(this.xxPos - Other.xxPos, 2) + Math.pow(this.yyPos - Other.yyPos, 2));
        return Distance;
    }

    /** Calculate the force exerted on this planet by the given planet */
    public double calcForceExertedBy(Planet Other) {
        double Force, distance;
        distance = this.calcDistance(Other);
        Force = G * this.mass * Other.mass / Math.pow(distance, 2);
        return Force;
    }

    public double calcForceExertedByX(Planet Other) {
        double dx, ForceX;
        dx = this.xxPos - Other.xxPos;
        if (dx > 0) {
            ForceX = this.calcForceExertedBy(Other) * dx / this.calcDistance(Other);  
        } else {
            ForceX = -1 * this.calcForceExertedBy(Other) * dx / this.calcDistance(Other); 
        }
        return ForceX; 
    }

    public double calcForceExertedByY(Planet Other) {
        double dy, ForceY;
        dy = this.yyPos - Other.yyPos;
        if (dy > 0) {
            ForceY = this.calcForceExertedBy(Other) * dy / this.calcDistance(Other);  
        } else {
            ForceY = -1 * this.calcForceExertedBy(Other) * dy / this.calcDistance(Other);  
        }
        return ForceY; 
    }

    public double calcNetForceExertedByX(Planet[] Others) {
        double NetForceX = 0;
        int Index = 0;
        while (Index < Others.length) {
            if (this.equals(Others[Index])) {
                Index += 1;
                continue;
            }
            if (this.xxPos < Others[Index].xxPos){
                NetForceX += calcForceExertedByX(Others[Index]);
            } else {
                NetForceX -= calcForceExertedByX(Others[Index]); 
            }
            Index += 1;
        }
        return NetForceX;
    }

    public double calcNetForceExertedByY(Planet[] Others) {
        double NetForceY = 0;
        int Index = 0;
        while (Index < Others.length) {
            if (this.equals(Others[Index])) {
                Index += 1;
                continue;
            }
            if (this.yyPos < Others[Index].yyPos){
                NetForceY += calcForceExertedByY(Others[Index]);
            } else {
                NetForceY -= calcForceExertedByY(Others[Index]); 
            }
            Index += 1;
        }     
        return NetForceY;
    }

    public void update(double time, double ForceX, double ForceY) {
        double ax, ay;
        ax = ForceX / this.mass;
        ay = ForceY / this.mass;
        this.xxVel += ax * time;
        this.yyVel += ay * time;
        this.xxPos += this.xxVel * time;
        this.yyPos += this.yyVel * time; 
    }

    public void draw() {
        String imageToDraw = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);
    }
}