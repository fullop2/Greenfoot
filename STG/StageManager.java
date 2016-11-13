import  java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import greenfoot.*;

/**
 * Write a description of class StageManager here.
 * 스테이지를 로딩하는 함수 stageN.txt로부터 정보를 받은 뒤 파싱하여 배열에 저장해둔다
 * @fullop (your name) 
 * @version (a version number or a date)
 */
public class StageManager  
{
    // 몹 정보와 생성 시간을 저장하는 공간
    private Enemy enemyPool[]; 
    private double Time[];
    
    // 몹의 개수와 타입을 임시로 저장하는 공간
    private int MAX;
    private int cnt = 0;
    private int input = 0;
    
    // Boss
    private Boss boss;
    private ShotBullet shotBullets[];
    private int health[];
    
    // Mob
    private Mob mob;
    private ShotBullet shotBullet;
    private Path path[];
    private enum BulletType{Red,Blue,Green};
    
    // StageStatus
    private int nowStage = 1;
    
    // IOBuffer
    private BufferedReader br;
    private String line;
    private StringTokenizer tokens;
    
    // 생성자
    public StageManager()  
    {
             
    }
    
    // 메모리를 초기화 하는 함수
    private void Init()
    {
        cnt = 0;
        enemyPool = new Enemy[300];
        Time = new double[300];
    }
    
    // world에서 적을 불러오는데 사용되는 함수
    public Enemy getEnemy(double t)
    {
        if(cnt < MAX)
        {
            if(t >= Time[cnt])
            {     
                return enemyPool[cnt++];
            }
            else
            return null;
        }
        else
        return null;
    }
    
    // 월드 초기화시 그 스테이지의 적을 로딩하는 함수
    public void loadEnemy(int stageNumber) 
    {
            try{
                ParseEnemy(stageNumber);
        }
        catch(IOException e)
        {

        }
    }
    
    private void ParseEnemy(int stageNumber) throws IOException 
    {
        Init();
        br = new BufferedReader(new FileReader("stage" + stageNumber + ".dat"));
        while(true)
        {
            line = br.readLine();
            if(line == null) break;
            tokens = new StringTokenizer( line, ", " );
            Time[cnt] = Double.parseDouble(tokens.nextToken());
            line = br.readLine();
            tokens = new StringTokenizer( line, ", " );
            input = Integer.parseInt(tokens.nextToken());
            if(input == 1)
            {
                ParseBoss();
            }
            else
            {
                ParseMob();
            }
            cnt++;
        }
        MAX = cnt;
        cnt = 0;
        br.close();
    }
    
    
    private void ParseBoss() throws IOException 
    {
         boss = new Boss();
         health = new int[6];
         boss.setPosition(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
         boolean randomMove = Boolean.parseBoolean(tokens.nextToken());                            // parse randomMove
         int patternNumber = Integer.parseInt(tokens.nextToken());                               // parse patternNumber
         shotBullets = new ShotBullet[patternNumber];
         for(int pattern = 0; pattern < patternNumber; pattern++)
         {
             line = br.readLine();
             tokens = new StringTokenizer( line, ", " );
             health[pattern] = Integer.parseInt(tokens.nextToken());                             // parse spellHealth
             shotBullets[pattern] = parseBulletController(boss);
            }
            boss.setBoss(health,shotBullets,patternNumber,randomMove);
            enemyPool[cnt] = boss;
    }
    
    private void ParseMob() throws IOException
    {
        mob = new Mob();
        mob.setPosition(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
        int pathNum = Integer.parseInt(tokens.nextToken());
        int moveSpeed = Integer.parseInt(tokens.nextToken());
        int health = Integer.parseInt(tokens.nextToken());
        
        line = br.readLine();
        tokens = new StringTokenizer( line, ", " );
        shotBullet = parseBulletController(mob);
        path = new Path[pathNum];
        for(int p = 0; p < pathNum; p++)
        {
            line = br.readLine();
            tokens = new StringTokenizer( line, ", " );
            path[p] = new Path(Integer.parseInt(tokens.nextToken()),
            Integer.parseInt(tokens.nextToken()),
            Integer.parseInt(tokens.nextToken()));
        }
        mob.set(path,shotBullet,pathNum,moveSpeed,health);  
        enemyPool[cnt] = mob;
    }
    
    private ShotBullet parseBulletController(Enemy follower)
    {
        int increaseSpeed = Integer.parseInt(tokens.nextToken());
        int bulletType = Integer.parseInt(tokens.nextToken());
        if(increaseSpeed == 0)
        {
            return new NormalShot(Integer.parseInt(tokens.nextToken()),            // parse AvailTime
                                  follower,                                                   // parse follower
                                  bulletType,
                                  parseEnemyBullet(bulletType),
                                  //  parse bullet speed, turn, rotating value
                                  Integer.parseInt(tokens.nextToken()), // parse bullet mainNumber
                                  Integer.parseInt(tokens.nextToken()), // parse bullet mainDelay
                                  Integer.parseInt(tokens.nextToken()), // parse bullet subNumber
                                  Integer.parseInt(tokens.nextToken()), // parse bullet subDelay
                                  Integer.parseInt(tokens.nextToken()), // parse bullet aimAngle
                                  Boolean.parseBoolean(tokens.nextToken()), // parse playerAim
                                  Boolean.parseBoolean(tokens.nextToken()), // parse be rotating
                                  Integer.parseInt(tokens.nextToken()));    // parse rotate value
        }
        else
        {
             return new ExplosionShot(Integer.parseInt(tokens.nextToken()),            // parse AvailTime
                                  follower,                                                   // parse follower
                                  bulletType,
                                  parseEnemyBullet(bulletType),
                                  //  parse bullet speed, turn, rotating value
                                  Integer.parseInt(tokens.nextToken()), // parse bullet mainNumber
                                  Integer.parseInt(tokens.nextToken()), // parse bullet mainDelay
                                  Integer.parseInt(tokens.nextToken()), // parse bullet subNumber
                                  Integer.parseInt(tokens.nextToken()), // parse bullet subDelay
                                  Integer.parseInt(tokens.nextToken()), // parse bullet aimAngle
                                  Boolean.parseBoolean(tokens.nextToken()), // parse playerAim
                                  Boolean.parseBoolean(tokens.nextToken()), // parse be rotating
                                  Integer.parseInt(tokens.nextToken()),   // parse rotate value
                                  increaseSpeed);   // parse increaseSpeed 
        }
    }
    
    private EnemyBullet parseEnemyBullet(int bulletType)
    {
        EnemyBullet enemyBullet;
       
        switch(bulletType)
        {
            case 0:
            enemyBullet = new EnemyBulletRed(Integer.parseInt(tokens.nextToken()),0,Integer.parseInt(tokens.nextToken()));
            break;
            case 1:
            enemyBullet = new EnemyBulletGreen(Integer.parseInt(tokens.nextToken()),0,Integer.parseInt(tokens.nextToken()));
            break;
            default:
            enemyBullet = new EnemyBulletBlue(Integer.parseInt(tokens.nextToken()),0,Integer.parseInt(tokens.nextToken()));
            break;
        }
        
        return enemyBullet;
    }
}
