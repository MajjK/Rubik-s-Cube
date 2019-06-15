import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.*;
import javax.swing.*;
import java.awt.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;




public class Rubik extends JFrame implements KeyListener{

    
   
public ArrayList<com.sun.j3d.utils.geometry.Box> cubes_arr;
public ArrayList<Transform3D> transformations;
public ArrayList<TransformGroup> transformations_group;
public ArrayList<Transform3D> cubes_transformations;
public ArrayList<BranchGroup> bg;
private TransformGroup root_trans_group;

public int plane=0;
public int[][][] matrix = new int[3][3][3];
public int[][][] matrix_color = new int[3][3][6];
public int[][][] matrix_color_buf = new int[3][3][6];
public boolean changed = false;

public Material box_material_yellow_chosen;
public Material box_material_white_chosen;
public Material box_material_blue_chosen;
public Material box_material_red_chosen;
public Material box_material_green_chosen;
public Material box_material_purple_chosen;
public Material box_material_yellow;
public Material box_material_white;
public Material box_material_blue;
public Material box_material_red;
public Material box_material_green;
public Material box_material_purple;
public Material box_material_black;

public Appearance yellow;
public Appearance white;
public Appearance red;
public Appearance green;
public Appearance blue;
public Appearance purple;

public Appearance yellow_chosen;
public Appearance white_chosen;
public Appearance red_chosen;
public Appearance green_chosen;
public Appearance blue_chosen;
public Appearance purple_chosen;
public Appearance black;


    public Rubik(){
        
       
        
        super("Rubik's Cube");
        
    box_material_yellow_chosen = new Material();
     box_material_yellow_chosen.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_yellow_chosen.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_yellow_chosen.setAmbientColor(1.0f,1.0f,0.0f);
    box_material_white_chosen = new Material();
     box_material_white_chosen.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_white_chosen.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_white_chosen.setAmbientColor(1.0f,1.0f,1.0f);
    box_material_blue_chosen = new Material();
     box_material_blue_chosen.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_blue_chosen.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_blue_chosen.setAmbientColor(0.0f,0.0f,1.0f);
     box_material_red_chosen = new Material();
     box_material_red_chosen.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_red_chosen.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_red_chosen.setAmbientColor(1.0f,0.0f,0.0f);
     box_material_green_chosen = new Material();
     box_material_green_chosen.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_green_chosen.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_green_chosen.setAmbientColor(0.0f,1.0f,0.0f);
    box_material_purple_chosen = new Material();
     box_material_purple_chosen.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_purple_chosen.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_purple_chosen.setAmbientColor(1.0f,0f,1.0f);
     
   box_material_yellow = new Material();
     box_material_yellow.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_yellow.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_yellow.setAmbientColor(0.75f,0.75f,0.0f);
     box_material_white = new Material();
     box_material_white.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_white.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_white.setAmbientColor(0.75f,0.75f,0.75f);
     box_material_blue = new Material();
     box_material_blue.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_blue.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_blue.setAmbientColor(0.0f,0.0f,0.75f);
     box_material_red = new Material();
     box_material_red.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_red.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_red.setAmbientColor(0.75f,0.0f,0.0f);
     box_material_green = new Material();
     box_material_green.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_green.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_green.setAmbientColor(0.0f,0.75f,0.0f);
     box_material_purple = new Material();
     box_material_purple.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_purple.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_purple.setAmbientColor(0.75f,0f,0.75f);   
     box_material_black = new Material();
     box_material_black.setCapability(Material.ALLOW_COMPONENT_WRITE);
     box_material_black.setCapability(Material.ALLOW_COMPONENT_READ);
     box_material_black.setAmbientColor(0f,0f,0f);   
     
     
     
     yellow = new Appearance();
    yellow.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    yellow.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  yellow.setColoringAttributes(new ColoringAttributes(1.0f,1.0f,0.0f,ColoringAttributes.NICEST));
    yellow.setMaterial(box_material_yellow);
    
     white = new Appearance();
    white.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    white.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  white.setColoringAttributes(new ColoringAttributes(1.0f,1.0f,1.0f,ColoringAttributes.NICEST));
    white.setMaterial(box_material_white);
     
   blue = new Appearance();
     blue.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    blue.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  blue.setColoringAttributes(new ColoringAttributes(0.0f,0.0f,1.0f,ColoringAttributes.NICEST));
    blue.setMaterial(box_material_blue);
    
   red = new Appearance();
    red.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    red.setCapability(Appearance.ALLOW_MATERIAL_READ);
   // red.setColoringAttributes(new ColoringAttributes(1.0f,0.0f,0.0f,ColoringAttributes.NICEST));
    red.setMaterial(box_material_red);
     
   green = new Appearance();
    green.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    green.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  green.setColoringAttributes(new ColoringAttributes(0.0f,1.0f,0.0f,ColoringAttributes.NICEST));
    green.setMaterial(box_material_green);
     
   purple = new Appearance();
      purple.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    purple.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  purple.setColoringAttributes(new ColoringAttributes(1.0f,0.0f,1.0f,ColoringAttributes.NICEST));
    purple.setMaterial(box_material_purple);
   
     
     
     
     
    yellow_chosen = new Appearance();
    yellow_chosen.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    yellow_chosen.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  yellow.setColoringAttributes(new ColoringAttributes(1.0f,1.0f,0.0f,ColoringAttributes.NICEST));
    yellow_chosen.setMaterial(box_material_yellow_chosen);
    
     white_chosen = new Appearance();
    white_chosen.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    white_chosen.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  white.setColoringAttributes(new ColoringAttributes(1.0f,1.0f,1.0f,ColoringAttributes.NICEST));
    white_chosen.setMaterial(box_material_white_chosen);
     
     blue_chosen = new Appearance();
     blue_chosen.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    blue_chosen.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  blue.setColoringAttributes(new ColoringAttributes(0.0f,0.0f,1.0f,ColoringAttributes.NICEST));
    blue_chosen.setMaterial(box_material_blue_chosen);
    
     red_chosen = new Appearance();
    red_chosen.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    red_chosen.setCapability(Appearance.ALLOW_MATERIAL_READ);
   // red.setColoringAttributes(new ColoringAttributes(1.0f,0.0f,0.0f,ColoringAttributes.NICEST));
    red_chosen.setMaterial(box_material_red_chosen);
     
    green_chosen = new Appearance();
    green_chosen.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    green_chosen.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  green.setColoringAttributes(new ColoringAttributes(0.0f,1.0f,0.0f,ColoringAttributes.NICEST));
    green_chosen.setMaterial(box_material_green_chosen);
     
    purple_chosen = new Appearance();
      purple_chosen.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    purple_chosen.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  purple.setColoringAttributes(new ColoringAttributes(1.0f,0.0f,1.0f,ColoringAttributes.NICEST));
    purple_chosen.setMaterial(box_material_purple_chosen);
   
    
    black = new Appearance();
      black.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    black.setCapability(Appearance.ALLOW_MATERIAL_READ);
  //  purple.setColoringAttributes(new ColoringAttributes(1.0f,0.0f,1.0f,ColoringAttributes.NICEST));
    black.setMaterial(box_material_black);
     
     
     
     
        
        cubes_arr =  new ArrayList<com.sun.j3d.utils.geometry.Box>();
        transformations = new ArrayList<Transform3D>();
        transformations_group = new ArrayList<TransformGroup>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

    
        GraphicsConfiguration config =
           SimpleUniverse.getPreferredConfiguration();

        Canvas3D canvas3D = new Canvas3D(config);
        canvas3D.setPreferredSize(new Dimension(1280,940));

        add(canvas3D);
        pack();
        setVisible(true);
        canvas3D.addKeyListener(this);

        BranchGroup scene = create_scene();
	    scene.compile();

        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

        Transform3D observ_pos = new Transform3D();
        observ_pos.set(new Vector3f(0.0f,0.0f,30.0f));
        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(new BoundingSphere());

        simpleU.getViewingPlatform().setViewPlatformBehavior(orbit);
        simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(observ_pos);

        simpleU.addBranchGraph(scene);

       
    }

    public void init_cubes()
{

    // Creating cubes
    for(int i=0;i<27;i++)
    {
        com.sun.j3d.utils.geometry.Box box =new com.sun.j3d.utils.geometry.Box();
        box.setCapability(com.sun.j3d.utils.geometry.Box.ENABLE_APPEARANCE_MODIFY);
        box.setCapability(com.sun.j3d.utils.geometry.Box.ALLOW_CHILDREN_READ);
        box.getShape(Box.FRONT).setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        box.getShape(Box.FRONT).setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        box.getShape(Box.BACK).setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        box.getShape(Box.BACK).setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        box.getShape(Box.LEFT).setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        box.getShape(Box.LEFT).setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        box.getShape(Box.RIGHT).setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        box.getShape(Box.RIGHT).setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        box.getShape(Box.TOP).setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        box.getShape(Box.TOP).setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        box.getShape(Box.BOTTOM).setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        box.getShape(Box.BOTTOM).setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        box.setCapability(com.sun.j3d.utils.geometry.Box.GEOMETRY_NOT_SHARED);
        cubes_arr.add(box);
    }
    
    
    for(int i=0;i<27;i++)
    {
    cubes_arr.get(i).getShape(Box.FRONT).setAppearance(red);
    cubes_arr.get(i).getShape(Box.BACK).setAppearance(green);
    cubes_arr.get(i).getShape(Box.RIGHT).setAppearance(purple);
    cubes_arr.get(i).getShape(Box.LEFT).setAppearance(blue);
    cubes_arr.get(i).getShape(Box.TOP).setAppearance(white);
    cubes_arr.get(i).getShape(Box.BOTTOM).setAppearance(yellow);
    }
    
    
    //Placing Cubes
     float x =-2.1f;
    int xm=0;
    float y =-2.1f;
    int ym=0;
    float z =-2.1f;
    int zm=0;
    for(int i=0;i<27;i++)
    {
    if(x==4.2f){x=-2.1f;y+=2.1f;xm=0;ym++;}
    if(y==4.2f){y=-2.1f;z+=2.1f;ym=0;zm++;}
      matrix[xm][ym][zm]=i;

    Vector3f vector = new Vector3f(x,y,z);
    Transform3D  tmp_rot      = new Transform3D();
    tmp_rot.rotX(0);

      
        transformations.add(new Transform3D());
        transformations.get(i).set(vector);
    //    cubes_transformations.add(new Transform3D());
        
 //       cubes_transformations.get(i).mul(tmp_rot);
        x+=2.1f;
        xm++;
        
   
        
    }
     Transform3D starting_view = new Transform3D();
        starting_view.set(new Vector3f(0.0f,0.0f,0.0f));
        starting_view.rotX(0.001d);
        root_trans_group.setTransform(starting_view);
    //creating matrix of colours    
        
      for(int i=0; i < 6; i++){
          for (int j=0; j < 3; j++){
              for(int k=0; k < 3; k++){
                          matrix_color[k][j][i] = i;
                          matrix_color_buf[k][j][i] = i;
              }
          }
      }
      

    //Connecting Cubes to the transformation
    for (int i=0;i<27;i++)
    {
        
    
    transformations_group.add(new TransformGroup());
    transformations_group.get(i).setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    transformations_group.get(i).setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    transformations_group.get(i).setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
    transformations_group.get(i).setCapability(TransformGroup.ALLOW_CHILDREN_READ);
    transformations_group.get(i).setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        transformations_group.get(i).setTransform(transformations.get(i));

 
   /* 
    BranchGroup branchg = new BranchGroup();
    branchg.addChild(cubes_arr.get(i));
    bg.add(branchg);
    bg.get(i).setCapability(BranchGroup.ALLOW_DETACH);*/
    transformations_group.get(i).addChild(cubes_arr.get(i));
    transformations_group.get(i).setCapability(com.sun.j3d.utils.geometry.Box.ALLOW_CHILDREN_WRITE);
    transformations_group.get(i).setCapability(com.sun.j3d.utils.geometry.Box.ALLOW_CHILDREN_READ);
   // root_trans_group.addChild(transformations_group.get(i));

    
     
    
      root_trans_group.addChild(transformations_group.get(i));
    }
  /*  for (int i=0;i<9;i++){
    root_trans_group.removeChild(transformations_group.get(i));
    obrot_animacja_x.addChild(transformations_group.get(i));}*/
}
    
   BranchGroup create_scene(){

      BranchGroup scene_root = new BranchGroup();

 
      
    root_trans_group = new TransformGroup();
    root_trans_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    root_trans_group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    root_trans_group.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
    root_trans_group.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
    root_trans_group.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
    scene_root.addChild(root_trans_group);

      
     BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0, 0, 0), 1000);

      BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 1000);
      BoundingSphere bounds2 = new BoundingSphere();
   /*   
             obrot_animacja_x = new TransformGroup();
      obrot_animacja_x.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      scene_root.addChild(obrot_animacja_x);

      Alpha alpha_animacja = new Alpha(1,2000);

      RotationInterpolator obracacz = new RotationInterpolator(alpha_animacja, obrot_animacja_x);
      final float var = (float) Math.PI/2;
    obracacz.setMaximumAngle(var);
     
      obracacz.setSchedulingBounds(bounds2);
      obrot_animacja_x.addChild(obracacz);
   */
      //LIGHTS

      BoundingSphere boundsL = new BoundingSphere(new Point3d(0, 0, 0), 1000000);
      AmbientLight lightA = new AmbientLight(new Color3f(1f,1f,1f));
      lightA.setInfluencingBounds(boundsL);
      scene_root.addChild(lightA);

      DirectionalLight lightD = new DirectionalLight();
      lightD.setInfluencingBounds(bounds);
      lightD.setDirection(new Vector3f(0f, 2.1f, 0f));
      lightD.setColor(new Color3f(0.1f, 0.1f, 0.1f));

      
          init_cubes();
       

      return scene_root;

    }   
      
      
      
   public void set_colors(int which , int color, int wall)
   {
        switch(color){
            case 0:
                   cubes_arr.get(which).getShape(wall).setAppearance(red);
                break;
            case 1:
                   cubes_arr.get(which).getShape(wall).setAppearance(green);
                break;
            case 2:
                   cubes_arr.get(which).getShape(wall).setAppearance(purple);
                break;                
            case 3:
                   cubes_arr.get(which).getShape(wall).setAppearance(blue);
                break;            
            case 4:
                   cubes_arr.get(which).getShape(wall).setAppearance(white);                
                break;            
            case 5:
                   cubes_arr.get(which).getShape(wall).setAppearance(yellow);        
                break;            
        }
   }        
           
   public void reset_colors()
   {
       
   
    for(int i=0;i<27;i++)
    {
    cubes_arr.get(i).getShape(Box.FRONT).setAppearance(red);
    cubes_arr.get(i).getShape(Box.BACK).setAppearance(green);
    cubes_arr.get(i).getShape(Box.RIGHT).setAppearance(purple);
    cubes_arr.get(i).getShape(Box.LEFT).setAppearance(blue);
    cubes_arr.get(i).getShape(Box.TOP).setAppearance(white);
    cubes_arr.get(i).getShape(Box.BOTTOM).setAppearance(yellow);    
    }
    
   for(int k=0;k<6;k++)
    {
        for(int i=0; i<3 ; i++)
        {
            for(int j=0; j<3; j++)
            {   
                    
                if(k==0) set_colors(matrix[i][j][2],matrix_color[i][j][k],k);
                if(k==1) set_colors(matrix[2-i][j][0],matrix_color[i][j][k],k);
                if(k==2) set_colors(matrix[2][j][2-i],matrix_color[i][j][k],k);
                if(k==3) set_colors(matrix[0][j][i],matrix_color[i][j][k],k);                                            
                if(k==4) set_colors(matrix[i][2][2-j],matrix_color[i][j][k],k);
                if(k==5) set_colors(matrix[i][0][j],matrix_color[i][j][k],k);
                
            }
        }
    }
   }    

  
   public void set_colors_chosen(int which , int color, int wall)
   {
        switch(color){
            case 0:
                   cubes_arr.get(which).getShape(wall).setAppearance(red_chosen);
                break;
            case 1:
                   cubes_arr.get(which).getShape(wall).setAppearance(green_chosen);
                break;
            case 2:
                   cubes_arr.get(which).getShape(wall).setAppearance(purple_chosen);
                break;                
            case 3:
                   cubes_arr.get(which).getShape(wall).setAppearance(blue_chosen);
                break;            
            case 4:
                   cubes_arr.get(which).getShape(wall).setAppearance(white_chosen);                
                break;            
            case 5:
                   cubes_arr.get(which).getShape(wall).setAppearance(yellow_chosen);        
                break;            
        }
   }       

   public void rotate()
   {
    for(int j=0;j<3;j++)
    {
       
       switch(plane)
   {
       case 0:
       {  
           
       matrix_color_buf[j][2][3] = matrix_color[0][j][3];   
       matrix_color_buf[2][2-j][3] = matrix_color[j][2][3];          
       matrix_color_buf[2-j][0][3] = matrix_color[2][2-j][3];
       matrix_color_buf[0][j][3] = matrix_color[2-j][0][3];
    
       if(j == 0 || j == 1){
       matrix_color[j][2][3] = matrix_color_buf[j][2][3];
       matrix_color[2][2-j][3] = matrix_color_buf[2][2-j][3];
       matrix_color[2-j][0][3] = matrix_color_buf[2-j][0][3];
       matrix_color[0][j][3] = matrix_color_buf[0][j][3];
       }
        
      matrix_color_buf[0][j][0] = matrix_color[0][j][4];
      matrix_color_buf[0][j][5] = matrix_color[0][j][0];
      matrix_color_buf[2][j][1] = matrix_color[0][j][5];
      matrix_color_buf[0][j][4] = matrix_color[2][j][1];  
       
      matrix_color[0][j][0] = matrix_color_buf[0][j][0];
      matrix_color[0][j][5] = matrix_color_buf[0][j][5];
      matrix_color[2][j][1] = matrix_color_buf[2][j][1];
      matrix_color[0][j][4] = matrix_color_buf[0][j][4];         
           
       }
           break;
       case 1:
       {

       matrix_color_buf[1][j][0] = matrix_color[1][j][4];
       matrix_color_buf[1][j][5] = matrix_color[1][j][0];
       matrix_color_buf[1][j][1] = matrix_color[1][j][5];
       matrix_color_buf[1][j][4] = matrix_color[1][j][1];  
       
       matrix_color[1][j][0] = matrix_color_buf[1][j][0];
       matrix_color[1][j][5] = matrix_color_buf[1][j][5];
       matrix_color[1][j][1] = matrix_color_buf[1][j][1];
       matrix_color[1][j][4] = matrix_color_buf[1][j][4];    
       
       
           
       }
           break;
       case 2:
       {
           
       matrix_color_buf[j][2][2] = matrix_color[2][2-j][2];   
       matrix_color_buf[0][j][2] = matrix_color[j][2][2];          
       matrix_color_buf[2-j][0][2] = matrix_color[0][j][2];
       matrix_color_buf[2][2-j][2] = matrix_color[2-j][0][2];
    
   if(j==0 || j == 1){
       matrix_color[j][2][2] = matrix_color_buf[j][2][2];
       matrix_color[0][j][2] = matrix_color_buf[0][j][2];
       matrix_color[2-j][0][2] = matrix_color_buf[2-j][0][2];
       matrix_color[2][2-j][2] = matrix_color_buf[2][2-j][2];
   }

       matrix_color_buf[2][j][0] = matrix_color[2][j][4];
       matrix_color_buf[2][j][5] = matrix_color[2][j][0];
       matrix_color_buf[0][j][1] = matrix_color[2][j][5];
       matrix_color_buf[2][j][4] = matrix_color[0][j][1];  
      
       matrix_color[2][j][0] = matrix_color_buf[2][j][0];
       matrix_color[2][j][5] = matrix_color_buf[2][j][5];
       matrix_color[0][j][1] = matrix_color_buf[0][j][1];
       matrix_color[2][j][4] = matrix_color_buf[2][j][4];  
       
      

       }
           break;
       case 3:
       {
       matrix_color_buf[j][2][5] = matrix_color[0][j][5];   
       matrix_color_buf[2][2-j][5] = matrix_color[j][2][5];          
       matrix_color_buf[2-j][0][5] = matrix_color[2][2-j][5];
       matrix_color_buf[0][j][5] = matrix_color[2-j][0][5];
    
       if(j == 0 || j == 1){
       matrix_color[j][2][5] = matrix_color_buf[j][2][5];
       matrix_color[2][2-j][5] = matrix_color_buf[2][2-j][5];
       matrix_color[2-j][0][5] = matrix_color_buf[2-j][0][5];
       matrix_color[0][j][5] = matrix_color_buf[0][j][5];
       }    
           
           
       matrix_color_buf[j][0][0] = matrix_color[j][0][3];
       matrix_color_buf[j][0][2] = matrix_color[j][0][0];
       matrix_color_buf[j][0][1] = matrix_color[j][0][2];
       matrix_color_buf[j][0][3] = matrix_color[j][0][1];  
       
       matrix_color[j][0][0] = matrix_color_buf[j][0][0];
       matrix_color[j][0][2] = matrix_color_buf[j][0][2];
       matrix_color[j][0][1] = matrix_color_buf[j][0][1];
       matrix_color[j][0][3] = matrix_color_buf[j][0][3];    
       
       

       }
           break;
       case 4:
       {

       matrix_color_buf[j][1][0] = matrix_color[j][1][3];
       matrix_color_buf[j][1][2] = matrix_color[j][1][0];
       matrix_color_buf[j][1][1] = matrix_color[j][1][2];
       matrix_color_buf[j][1][3] = matrix_color[j][1][1];  
       
       matrix_color[j][1][0] = matrix_color_buf[j][1][0];
       matrix_color[j][1][2] = matrix_color_buf[j][1][2];
       matrix_color[j][1][1] = matrix_color_buf[j][1][1];
       matrix_color[j][1][3] = matrix_color_buf[j][1][3];    
       
       

       }
           break;
       case 5:
       {
           
       matrix_color_buf[j][2][4] = matrix_color[2][2-j][4];   
       matrix_color_buf[0][j][4] = matrix_color[j][2][4];          
       matrix_color_buf[2-j][0][4] = matrix_color[0][j][4];
       matrix_color_buf[2][2-j][4] = matrix_color[2-j][0][4];
    
   if(j==0 || j == 1){
       matrix_color[j][2][4] = matrix_color_buf[j][2][4];
       matrix_color[0][j][4] = matrix_color_buf[0][j][4];
       matrix_color[2-j][0][4] = matrix_color_buf[2-j][0][4];
       matrix_color[2][2-j][4] = matrix_color_buf[2][2-j][4];
   }           

       matrix_color_buf[j][2][0] = matrix_color[j][2][3];
       matrix_color_buf[j][2][2] = matrix_color[j][2][0];
       matrix_color_buf[j][2][1] = matrix_color[j][2][2];
       matrix_color_buf[j][2][3] = matrix_color[j][2][1];  
       
       matrix_color[j][2][0] = matrix_color_buf[j][2][0];
       matrix_color[j][2][2] = matrix_color_buf[j][2][2];
       matrix_color[j][2][1] = matrix_color_buf[j][2][1];
       matrix_color[j][2][3] = matrix_color_buf[j][2][3]; 
       }
           break;
       case 6:
       {
       matrix_color_buf[j][2][1] = matrix_color[0][j][1];   
       matrix_color_buf[2][2-j][1] = matrix_color[j][2][1];          
       matrix_color_buf[2-j][0][1] = matrix_color[2][2-j][1];
       matrix_color_buf[0][j][1] = matrix_color[2-j][0][1];
    
       if(j == 0 || j == 1){
       matrix_color[j][2][1] = matrix_color_buf[j][2][1];
       matrix_color[2][2-j][1] = matrix_color_buf[2][2-j][1];
       matrix_color[2-j][0][1] = matrix_color_buf[2-j][0][1];
       matrix_color[0][j][1] = matrix_color_buf[0][j][1];
       }
           
           
           
       matrix_color_buf[j][2][4] = matrix_color[2][j][2];
       matrix_color_buf[0][j][3] = matrix_color[j][2][4];
       matrix_color_buf[j][0][5] = matrix_color[0][j][3];
       matrix_color_buf[2][j][2] = matrix_color[j][0][5];  
       
       matrix_color[j][2][4] = matrix_color_buf[j][2][4];
       matrix_color[0][j][3] = matrix_color_buf[0][j][3];
       matrix_color[j][0][5] = matrix_color_buf[j][0][5];
       matrix_color[2][j][2] = matrix_color_buf[2][j][2];   
       
       

       }
           break;
       case 7:
       {

       matrix_color_buf[j][1][4] = matrix_color[1][j][2];
       matrix_color_buf[1][j][3] = matrix_color[j][1][4];
       matrix_color_buf[j][1][5] = matrix_color[1][j][3];
       matrix_color_buf[1][j][2] = matrix_color[j][1][5];  
       
       matrix_color[j][1][4] = matrix_color_buf[j][1][4];
       matrix_color[1][j][3] = matrix_color_buf[1][j][3];
       matrix_color[j][1][5] = matrix_color_buf[j][1][5];
       matrix_color[1][j][2] = matrix_color_buf[1][j][2];  
                


       }
           break;
       case 8:
       {
           
       matrix_color_buf[j][2][0] = matrix_color[2][2-j][0];   
       matrix_color_buf[0][j][0] = matrix_color[j][2][0];          
       matrix_color_buf[2-j][0][0] = matrix_color[0][j][0];
       matrix_color_buf[2][2-j][0] = matrix_color[2-j][0][0];
    
   if(j==0 || j == 1){
       matrix_color[j][2][0] = matrix_color_buf[j][2][0];
       matrix_color[0][j][0] = matrix_color_buf[0][j][0];
       matrix_color[2-j][0][0] = matrix_color_buf[2-j][0][0];
       matrix_color[2][2-j][0] = matrix_color_buf[2][2-j][0];
   }           

       matrix_color_buf[j][0][4] = matrix_color[0][j][2];
       matrix_color_buf[2][j][3] = matrix_color[j][0][4];
       matrix_color_buf[j][2][5] = matrix_color[2][j][3];
       matrix_color_buf[0][j][2] = matrix_color[j][2][5];  
       
       matrix_color[j][0][4] = matrix_color_buf[j][0][4];
       matrix_color[2][j][3] = matrix_color_buf[2][j][3];
       matrix_color[j][2][5] = matrix_color_buf[j][2][5];
       matrix_color[0][j][2] = matrix_color_buf[0][j][2];   
       

       }
           break;           
   
      }
   }
   }
   
   
   public void switcher()
   {
   switch(plane)
   {
       case 0:
       {
           reset_colors();
           
       for(int i=0;i<3;i++)
       {
       for(int j=0;j<3;j++)
       {
       set_colors_chosen(matrix[0][i][j],matrix_color[j][i][3], 3);
       set_colors_chosen(matrix[0][i][j],matrix_color[0][i][0], 0);
       set_colors_chosen(matrix[0][i][j],matrix_color[2][i][1], 1); 
       set_colors_chosen(matrix[0][i][j],matrix_color[0][2-j][4], 4);
       set_colors_chosen(matrix[0][i][j],matrix_color[0][j][5], 5);  
       }
       }
        
       break;
       }
        case 1:
        {
           reset_colors();
            
       for(int i=0;i<3;i++)
       {
       for(int j=0;j<3;j++)
       {    
       set_colors_chosen(matrix[1][i][j],matrix_color[1][i][0], 0);
       set_colors_chosen(matrix[1][i][j],matrix_color[1][i][1], 1);
       set_colors_chosen(matrix[1][i][j],matrix_color[1][2-j][4], 4);
       set_colors_chosen(matrix[1][i][j],matrix_color[1][j][5], 5);       
       }
       }
       break;
        }
        case 2:
         {
           reset_colors();
       for(int i=0;i<3;i++)
       {
       for(int j=0;j<3;j++)
       {
       set_colors_chosen(matrix[2][i][j],matrix_color[2-j][i][2],2);
       set_colors_chosen(matrix[2][i][j],matrix_color[2][i][0],0);
       set_colors_chosen(matrix[2][i][j],matrix_color[0][i][1],1);
       set_colors_chosen(matrix[2][i][j],matrix_color[2][2-j][4],4);
       set_colors_chosen(matrix[2][i][j],matrix_color[2][j][5],5);  
       }
       }
       break;
         }
        case 3:
          {
          reset_colors();
       for(int i=0;i<3;i++)
       {
       for(int j=0;j<3;j++)
       {
       set_colors_chosen(matrix[i][0][j],matrix_color[i][j][5],5);
       set_colors_chosen(matrix[i][0][j],matrix_color[i][0][0],0);
       set_colors_chosen(matrix[i][0][j],matrix_color[2-i][0][1],1);
       set_colors_chosen(matrix[i][0][j],matrix_color[j][0][3],3);
       set_colors_chosen(matrix[i][0][j],matrix_color[2-j][0][2],2); 
           
       }
       }
       break;
          }
        case 4:
           {
           reset_colors();
       for(int i=0;i<3;i++)
       {
       for(int j=0;j<3;j++)
       {
       set_colors_chosen(matrix[i][1][j],matrix_color[i][1][0],0);
       set_colors_chosen(matrix[i][1][j],matrix_color[2-i][1][1],1);
       set_colors_chosen(matrix[i][1][j],matrix_color[j][1][3],3);
       set_colors_chosen(matrix[i][1][j],matrix_color[2-j][1][2],2); 
       }
       }
       break;
           }
            case 5:
            {
          reset_colors();
       for(int i=0;i<3;i++)
       {
       for(int j=0;j<3;j++)
       {
       set_colors_chosen(matrix[i][2][j],matrix_color[i][2-j][4],4);
       set_colors_chosen(matrix[i][2][j],matrix_color[i][2][0],0);
       set_colors_chosen(matrix[i][2][j],matrix_color[2-i][2][1],1);
       set_colors_chosen(matrix[i][2][j],matrix_color[j][2][3],3);
       set_colors_chosen(matrix[i][2][j],matrix_color[2-j][2][2],2); 
       }
       }
       break;
            }
             case 6:
             {
           reset_colors();
       for(int i=0;i<3;i++)
       {
       for(int j=0;j<3;j++)
       {
       set_colors_chosen(matrix[i][j][0],matrix_color[2-i][j][1],1);
       set_colors_chosen(matrix[i][j][0],matrix_color[0][j][3],3);
       set_colors_chosen(matrix[i][j][0],matrix_color[2][j][2],2);
       set_colors_chosen(matrix[i][j][0],matrix_color[i][2][4],4);
       set_colors_chosen(matrix[i][j][0],matrix_color[i][0][5],5); 
       }
       }
       break;
             }
              case 7:
              {
           reset_colors();
       for(int i=0;i<3;i++)
       {
       for(int j=0;j<3;j++)
       {
       set_colors_chosen(matrix[i][j][1],matrix_color[1][j][3],3);
       set_colors_chosen(matrix[i][j][1],matrix_color[1][j][2],2);
       set_colors_chosen(matrix[i][j][1],matrix_color[i][1][4],4);
       set_colors_chosen(matrix[i][j][1],matrix_color[i][1][5],5); 
       }
       }
       break;
              }
               case 8:
               {
          reset_colors();
       for(int i=0;i<3;i++)
       {
       for(int j=0;j<3;j++)
       {
       set_colors_chosen(matrix[i][j][2],matrix_color[i][j][0],0);
       set_colors_chosen(matrix[i][j][2],matrix_color[2][j][3],3);
       set_colors_chosen(matrix[i][j][2],matrix_color[0][j][2],2);
       set_colors_chosen(matrix[i][j][2],matrix_color[i][0][4],4);
       set_colors_chosen(matrix[i][j][2],matrix_color[i][2][5],5); 
       }
       }
       break;
               }
   }
   }      
      
      
      
    @Override
    public void keyPressed(KeyEvent e) {
        
    if (e.getKeyChar() == 'a') 
    {   if(changed==false){
        changed=true;
         if(plane==0)plane=8;
         else plane--;
         switcher();}
    }
    if (e.getKeyChar() == 'd') 
    {
        if(changed==false){
            changed=true;
         if(plane==8)plane=0;
         else plane++;
         switcher();
        }
    }
    if (e.getKeyChar() == 's') {
        rotate();
        switcher();
     //   reset_colors();
    }
    }
    
    
      @Override  
    public void keyReleased(KeyEvent e) {
    // Invoked when a key has been released.
       if (e.getKeyChar() == 'a') 
    {   changed=false;
    }
       if (e.getKeyChar() == 'd') 
    {   changed=false;
    }
  }
    
   @Override
    public void keyTyped(KeyEvent e) {
       
    }
  
   public static void main(String args[]){
       Rubik rubik =new Rubik();
       rubik.switcher();
       rubik.addKeyListener(rubik);
   }
   
}

