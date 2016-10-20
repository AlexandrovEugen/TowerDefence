package data;

import java.util.ArrayList;
import static helpers.Clock.*;



public class Wave {
    private float timeSinceLastSpawn, spawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy>  enemyList;
    private int enemiesPerWave;
    private boolean waveCompleted;


    public Wave(Enemy enemyType,float spawnTime, int enemiesPerWave){
        this.spawnTime = spawnTime;
        this.enemyType = enemyType;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new ArrayList<>();
        this.enemiesPerWave = enemiesPerWave;
        this.waveCompleted = false;
        spawn();
    }

    public void update(){
        boolean allEnemiesDead = true;
        if (enemyList.size() < enemiesPerWave) {
            timeSinceLastSpawn += Delta();
            if (timeSinceLastSpawn > spawnTime) {
                spawn();
                timeSinceLastSpawn = 0;
            }
        }
        for (Enemy e : enemyList) {
            if (e.isAlive()) {
                allEnemiesDead = false;
                e.update();
                e.draw();
            }
        }
        if (allEnemiesDead){
            waveCompleted = true;
        }
    }

    private void spawn() {
        enemyList.add(new Enemy(enemyType.getTexture(), enemyType.getStartTile(), enemyType.getTileGrid(), 64,64, enemyType.getSpeed(), enemyType.getHealth()));

    }

    public boolean isCompleted(){
        return waveCompleted;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
}
