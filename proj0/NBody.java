public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int Num = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int Num = in.readInt();
        int index = 0;
        double radius = in.readDouble();
        Planet[] planets = new Planet[Num];
        while (index < Num) {
            planets[index] = new Planet(
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readString()
            );
            index += 1;
        }
        return planets;
    }

    public static void main(String[] args) {
        //Read the information about planets.
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // Draw the universe.
        String imageToDraw = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw); 
       
        //Draw planets
        for(Planet p: planets) {
            p.draw();
        }
       
        StdDraw.enableDoubleBuffering();

        double times = 0;
        while (times < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            int Index = 0;
            for (Planet p : planets) {
                xForces[Index] = p.calcNetForceExertedByX(planets);
                yForces[Index] = p.calcNetForceExertedByY(planets);
                Index += 1;
            }

            int Index2 = 0;
            for (Planet p : planets){
                p.update(dt, xForces[Index2], yForces[Index2]);
                Index2 += 1;
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);

            for(Planet p: planets) {
                p.draw();
            } 

            StdDraw.show();
            StdDraw.pause(10);
            times += dt;
        } 

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}