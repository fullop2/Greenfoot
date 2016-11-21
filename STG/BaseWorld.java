import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

// 모든 월드의 베이스가 되는 클래스
// 공통으로 포함되는 UI 및 스테이터스를 관리할 예정
// 몹 소환같은 작동은 서브 클래스 월드에서 이루어진다.

public class BaseWorld extends World
{
   
   // StageManager
   private StageManager sm = new StageManager();
   private int nowStage;
   
   // background
   private GreenfootImage bg[] = new GreenfootImage[6];
   private GreenfootImage bg2 = new GreenfootImage(960,1024);
   private int bgDelta;
   
   // world state
   private boolean paused;
   
   // Timer
   private float frame = 1;
   private long prevTime;
   private double fps = 0.0f;
   
   // absorbItem
   private AbsorbItem absorbItem = new AbsorbItem();
   
   // DebugList
   private List<EnemyBullet> list = new ArrayList<EnemyBullet>();
   
   // Player
   public Player player;
   
   // Status Board
   private DoubleBoard powerBoard = new DoubleBoard();
   private zeroIntegerBoard scoreBoard = new zeroIntegerBoard();
   private IntegerBoard bombBoard = new IntegerBoard();
   private IntegerBoard grazeBoard = new IntegerBoard();
   private IntegerBoard pointBoard = new IntegerBoard();
   private IntegerBoard lifeBoard = new IntegerBoard();
   
    
   public void addBomb()
   {
       LifeUI lifeUI = new LifeUI();
       LifeStack.add(lifeUI);
       addObject(lifeUI,600+30*LifeStack.size(),300);
    }
   
   // GUI Status Display
   Stack<LifeUI> LifeStack = new Stack<LifeUI>();
   
   // Constructor
    public BaseWorld()
    {          
        super(960, 720, 1, false);
        Greenfoot.setSpeed(50);
        Init();   
    }
    

    // 기본 설정
    private void Init()
    {
        
        setPaintOrder(GUI.class,Player.class,Effect.class,Bullet.class,Weapon.class,Enemy.class,PlayerSprite.class,BombEffect.class,Items.class); // 드로잉 우선 순위
        
        frame = 1;
        
        MakeUI("UI-mainUI2.png",480,360);    // 그림이 있는 UI 객체 생성
        MakeUI("UI-score.png",650,150);
        MakeUI("UI-life.png",650,250);
        MakeUI("UI-bomb.png",655,290);
        MakeUI("UI-power.png",660,330); 
        
        bg[0] = new GreenfootImage("Stage1.png");
        bg[1] = new GreenfootImage("stage02.png");
        
        BackgroundSwap bgs = new BackgroundSwap(1,10,60,255);
        addObject(bgs,480,360);
        
        
        
        addObject(powerBoard,750,330);
        addObject(bombBoard,750,290);
        addObject(pointBoard,750,370);
        addObject(grazeBoard,750,410);
        addObject(scoreBoard,765,150);
        addObject(lifeBoard,750,250);
        
        loadingEnemy();
        SmoothMover.baseWorld = (BaseWorld)this;
        InitPlayer();
        StatusManager.GetInstance().Init();
        StatusManager.setWorld(this);
    }
    
    // 스테이지에 진입시 적을 로딩함
    private void loadingEnemy()
    {
        sm.loadEnemy(nowStage+1);
    }
    
    public void act()
        {
          if(!((Greenfoot.isKeyDown("escape") && (Greenfoot.isKeyDown("shift")))))
          {
              ++frame;
              UI();   // 수치 업데이트
              statusCheck();    // 플레이어, 월드 상태 체크
              SpawnEnemy();        
              bgScroll(); // 배경 스크롤
              stageChange();
              FPS();
        }
        else
           {
               removeObject(player);
               Greenfoot.setWorld(new Main());
            }
        }
    
    // 적을 호출하는 함수
   private void SpawnEnemy()
   {
       Enemy e = sm.getEnemy(frame/60);
       if(e != null)
       {
           addObject(e,e.gettX(),e.gettY());
           e.Init();
       }
    }
    
    // 플레이어 상태 체크 함수
   private void statusCheck()
        {
       if(StatusManager.GetInstance().Revival())
            {
                    InitPlayer();
            }
       if(StatusManager.GetInstance().isBombOn())
            {
               bulletClear();
               absorbItems();
            }
       else if(StatusManager.GetInstance().getAlive() && absorbItem.Line(player.getY()))
         {
             absorbItems();
         }
   }
   
   private void absorbItems()
   {
         absorbItem.mode = true;
         absorbItem.ItemList = getObjects(Items.class);
         absorbItem.absorb();       
    }
    
   // 배경 스크롤 함수
   private void bgScroll()
    {   
        if(bgDelta > bg2.getHeight())
            bgDelta = 0;
            else
            bgDelta++;
        for(int i = -bg2.getHeight(); i <= bg2.getHeight(); i += bg[nowStage].getHeight())
          {
            bg2.drawImage(bg[nowStage],52,bgDelta+i);
          }
            setBackground(bg2);    
   }
   
   private void InitPlayer()
   {
       player = new Player();
 
       addObject(player,300,600);  
       player.Init();
   }
 
   // 적 탄막 제거 함수
   public void bulletClear()
   {
            List<EnemyBullet> Bullets = getObjects(EnemyBullet.class);
            for(EnemyBullet bullet : Bullets)
            {
                bullet.Remove();
            }
   }
    
   
   // Stage가 바뀌었는지 체크하는 함수
   private void stageChange()
   {
       if(nowStage != StatusManager.GetInstance().getStage() && nowStage < 1 && frame > 0)
       {
           frame = -180;
           nowStage++;
           loadingEnemy();
        }
       if(frame == -60)
       {
           BackgroundSwap bgs = new BackgroundSwap(60,0,60,255);
           addObject(bgs,getWidth()/2,getHeight()/2);
        }
    }

   // UI 로딩 함수
   private void UI()
    {   
        StatusManager.GetInstance().act();
        powerBoard.UpdateField(StatusManager.GetInstance().getPower());
        scoreBoard.UpdateField(StatusManager.GetInstance().getScore());
        bombBoard.UpdateField(StatusManager.GetInstance().getBomb());
        grazeBoard.UpdateField(StatusManager.GetInstance().getGraze());
        pointBoard.UpdateField(StatusManager.GetInstance().getPoint());
        lifeBoard.UpdateField(StatusManager.GetInstance().getLife());  
        showText(String.format("%d",(int)frame), 610,700);
        showText(String.format("%.1f",fps),930,700);
        showText(Integer.toString(getObjects(EnemyBullet.class).size()), 610, 675);
    }
   
    // fps 출력 함수
    private void FPS()
    {
        if(frame % 10 == 0)
            {
                fps = 10000.0f / (System.currentTimeMillis() - prevTime) ;
              prevTime = System.currentTimeMillis();
            }
    }
    
    // UI 생성 함수
   private void MakeUI(String location, int xpos, int ypos)
    {
       GUI sprite = new GUI(location); // 아무것도 하지 않는 객체에 그림을 넣고 월드에 띄워줌
       addObject(sprite,xpos,ypos);
    }
    
    public void GoEnd()
    {
        GUI gameover = new GUI();
        gameover.setImage(new GreenfootImage("GameOver",60,Color.WHITE,new Color(0,0,0,0)));
        addObject(gameover,300,300);
        Greenfoot.delay(250);
        Greenfoot.setWorld(new ScoreWorld(StatusManager.GetInstance().getScore()));
    }
    
}
