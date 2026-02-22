package demo;
import model.Plan;
import model.Sphere;
import raytracing.LightSource;
import raytracing.Scene;
import utils.Vec3f;

/**
 * Creates and manages all available scenes.
 * 
 * @author KepsyIn
 */
public class SceneContainer {
	
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
	
	/**
	 * Retourne la description d'une scène.
	 */
	public static String getSceneDescription(int sceneNumber) {
		return switch(sceneNumber) {
			case 1 -> "The scene features two reflective spheres and a room made of planes. \n The left wall is a mirror, and both spheres are reflective. The room includes a back wall, floor, ceiling, and left and right walls.\n The back wall is green, the right wall is blue, and the ceiling and floor are close to cyan. There's a light source positioned at (10,1,1).";
			case 2 -> "The scene features a single blue sphere located at (0,0,-4). There is one light source positioned at (-10,5,5).";
			case 3 -> "The scene contains a green sphere positioned at (0,0,-10) and a back wall at (0,0,-40) with a reddish color.\n There are two light sources positioned at (10,0,0) and (-10,0,0), both emitting white light.";
			case 4 -> "The scene includes three spheres: one black sphere at (0,0,-10), a red sphere at (-3,0,-11), and a blue sphere at (3,0,-11).\n There is also a floor plane below the spheres, colored in yellowish-green. A single light source is positioned at (0,7,0), emitting white light. All spheres and the floor are reflective.";
			case 5 -> "The scene features a green/yellow sphere positioned at (0,0,-10) and a reflective black floor plane below it. A single light source is located at (0,10,-10), emitting white light.";
			default -> "Unknown scene";
		};
	}
	
	/**
	 * Retourne le nombre total de scènes disponibles.
	 */
	public static int getTotalScenes() {
		return 5;
	}
}
