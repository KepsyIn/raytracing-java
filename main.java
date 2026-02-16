import model.Plan;
import model.Sphere;
import raytracing.LightSource;
import raytracing.Scene;
import render.RenderTga;
import utils.Vec3f;

public class main {
	
	public static void main(String[] args) {
		
		int WIDTH = 800;
		int LENGTH = 600;
		
		Scene scene = new Scene();

		if( args.length <= 0 ) {
			System.err.println("missing arguments ( type -h for help )");
			return;
		} else {
			if( args[0].equals("-h")) {
				System.out.println("> the program take one arguments <number_of_the_scene> you want to generate.");
				System.out.println(" - there's 5 available scene on this raytracing program : "); // aide
				System.out.println("	1 - The scene features two reflective spheres and a room made of planes. \n The left wall is a mirror, and both spheres are reflective. The room includes a back wall, floor, ceiling, and left and right walls.\n The back wall is green, the right wall is blue, and the ceiling and floor are close to cyan. There's a light source positioned at (10,1,1).");
				System.out.println("	2 - The scene features a single blue sphere located at (0,0,-4). There is one light source positioned at (-10,5,5).");
				System.out.println("	3 - The scene contains a green sphere positioned at (0,0,-10) and a back wall at (0,0,-40) with a reddish color.\n There are two light sources positioned at (10,0,0) and (-10,0,0), both emitting white light.");
				System.out.println("	4 - The scene includes three spheres: one black sphere at (0,0,-10), a red sphere at (-3,0,-11), and a blue sphere at (3,0,-11).\n There is also a floor plane below the spheres, colored in yellowish-green. A single light source is positioned at (0,7,0), emitting white light. All spheres and the floor are reflective.");
				System.out.println("	5 - The scene features a green/yellow sphere positioned at (0,0,-10) and a reflective black floor plane below it. A single light source is located at (0,10,-10), emitting white light.");
				
				return;
			} else {
				try {
					int scene_number =	Integer.valueOf(args[0]);
					
					switch(scene_number) {
						case 1: scene = SceneContainer.buildScene1(); break;
						case 2: scene = SceneContainer.buildScene2(); break;
						case 3: scene = SceneContainer.buildScene3(); break;
						case 4: scene = SceneContainer.buildScene4(); break;
						case 5: scene = SceneContainer.buildScene5(); break;
						default: System.err.println("No scene associate to args : " + scene_number); return;
					}
				} catch ( NumberFormatException e ) {
					System.err.println("insert a valid scene number or type -h for help : " + e );
				}
			}
		}

		String filename = "raytracing_scene";
		
		boolean antialiasing = true;
		
		int samples = 4;
		
		try {
			System.out.println("> start raytracing");
			System.out.println(" - start generating the scene");
			byte scene_buffer[];
			if( antialiasing ) {
				System.out.println("  + generating scene with anti-aliasing");
				scene_buffer = scene.draw(WIDTH, LENGTH,samples);
			}
			else scene_buffer = scene.draw(WIDTH,LENGTH);
			System.out.println(" - finish generating the scene");
			
			System.out.println(" - start generating " + filename + ".tga file");
			RenderTga.saveTGA( filename + ".tga", scene_buffer  , WIDTH , LENGTH );
			System.out.println(" - finish generating " + filename + ".tga file");

			System.out.println("> finish raytracing");
		}catch (Exception e ) {
			System.err.println("TGA file not created :"+e);
		}
		
		
		
	}
	
	static class SceneContainer {
		public static Scene buildScene1() {
			Sphere sphere = new Sphere(1, new Vec3f(3,0,-15));
			Sphere otherSphere = new Sphere(1, new Vec3f(0,0,-10));
			
			otherSphere.setReflexionCoeff(1);
			sphere.setReflexionCoeff(0.5f);
			
			Plan backPlan = new Plan(new Vec3f(0,0,-50), new Vec3f(0,0,1));
			
			Plan planBehind = new Plan(new Vec3f(0,0,10), new Vec3f( 0,0,-1));
			Plan leftWall = new Plan(new Vec3f(-18,0,0), new Vec3f(1,0,0));
			
			leftWall.setReflexionCoeff(0.5f);
			
			Plan rightWall = new Plan(new Vec3f(18,0,0), new Vec3f(-1,0,0));
			Plan floor = new Plan(new Vec3f(0,-10,0), new Vec3f(0,1,0));
			Plan ceiling = new Plan(new Vec3f(0,10,0), new Vec3f(0,-1,0));
			
			Vec3f color = new Vec3f(0,0,1f);
			
			sphere.setColor(color);
			
			Scene scene_test = new Scene();
			
			scene_test.addLightSource(new LightSource(new Vec3f(1,1,1),new Vec3f(10,1,1)));
			
			backPlan.setColor(new Vec3f(0,1,0));
			leftWall.setColor(new Vec3f(0,0,0));
			rightWall.setColor(new Vec3f(1,0,0));
			ceiling.setColor(new Vec3f(0.8f,1,0));
			floor.setColor(new Vec3f(0.8f,1,0));
				
			scene_test.addModel(sphere);
			scene_test.addModel(otherSphere);
			scene_test.addModel(backPlan);
			scene_test.addModel(leftWall);
			scene_test.addModel(rightWall);
			scene_test.addModel(ceiling);
			scene_test.addModel(floor);
			scene_test.addModel(planBehind);
			
			return scene_test;
		}
		
		public static Scene buildScene2() {
			Scene scene = new Scene();
			scene.addLightSource(new LightSource(new Vec3f(1,1,1), new Vec3f(-10,5,5)));
			
			Sphere blue_sphere = new Sphere(1,new Vec3f(0,0,-4));
			blue_sphere.setColor(new Vec3f(1,0,0));
			
			scene.addModel(blue_sphere);
			
			return scene;
		}
		
		public static Scene buildScene3() {
			Scene scene = new Scene();
			
			scene.addLightSource(new LightSource(new Vec3f(1,1,1) , new Vec3f(10,0,0)));
			scene.addLightSource(new LightSource(new Vec3f(1,1,1) , new Vec3f(-10,0,0)));
			
			Plan backPlan = new Plan(new Vec3f(0,0,-40), new Vec3f(0,0,1));
			backPlan.setColor(new Vec3f(0.8f,0.2f,0.2f));
			
			Sphere sphere = new Sphere(2,new Vec3f(0,0,-10));
			sphere.setColor(new Vec3f(0,0.8f,0.8f));
			
			scene.addModel(sphere);
			scene.addModel(backPlan);
			
			return scene;
		}
		
		public static Scene buildScene4() {
			
			Scene scene = new Scene();
			scene.addLightSource(new LightSource(new Vec3f(1,1,1), new Vec3f(0,7,0)));
			
			Sphere sphere = new Sphere(1, new Vec3f(0,0,-10));
			sphere.setColor(new Vec3f(0,0,0));
			sphere.setReflexionCoeff(1);
			
			Sphere sphereLeft = new Sphere(1,new Vec3f(-3,0,-11));
			sphereLeft.setColor(new Vec3f(0.5f,0,0));
			sphereLeft.setReflexionCoeff(1);
			
			Sphere sphereRight = new Sphere(1,new Vec3f(3,0,-11));
			sphereRight.setColor(new Vec3f(0,0,0.5f));
			sphereRight.setReflexionCoeff(1);
			
			Plan floor = new Plan(new Vec3f(0,-1,0),new Vec3f(0,1,0));
			floor.setReflexionCoeff(0.2f);
			floor.setColor(new Vec3f(0.5f,0.5f,0));
			
			scene.addModel(sphere);
			scene.addModel(floor);
			scene.addModel(sphereLeft);
			scene.addModel(sphereRight);
			
			return scene;
		}
		
		public static Scene buildScene5() {
			
			Scene scene = new Scene();
			
			scene.addLightSource(new LightSource(new Vec3f(1,1,1),new Vec3f(0,10,-10)));
			
			Sphere sphere = new Sphere(1,new Vec3f(0,0,-10));
			sphere.setColor(new Vec3f(0.1f,0.8f,0.6f));
			
			Plan floor = new Plan(new Vec3f(0,-1,0),new Vec3f(0,1,0));
			floor.setColor(new Vec3f(0,0,0));
			floor.setReflexionCoeff(1);
			
			scene.addModel(floor);
			scene.addModel(sphere);
			
			return scene;
		}
	}
	
	


}
