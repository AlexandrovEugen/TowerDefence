package data;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static helpers.Clock.*;



public class Wave {
    private float timeSinceLastSpawn, spawnTime;
    private Enemy[] enemyTypes;
    private CopyOnWriteArrayList<Enemy>  enemyList;
    private int enemiesPerWave, enemySpawned;
    private boolean waveCompleted;


    public Wave(Enemy[] enemyTypes,float spawnTime, int enemiesPerWave){
        this.spawnTime = spawnTime;
        this.enemyTypes = enemyTypes;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new CopyOnWriteArrayList<>();
        this.enemiesPerWave = enemiesPerWave;
        this.enemySpawned = 0;
        this.waveCompleted = false;
        spawn();
    }

    public void update(){
        boolean allEnemiesDead = true;
        if (enemySpawned  < enemiesPerWave) {
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
            else {
                enemyList.remove(e);
            }
        }
        if (allEnemiesDead){
            waveCompleted = true;
        }
    }

    private void spawn() {
        int enemyChosen = 0;
        Random rnd = new Random();
        enemyChosen = rnd.nextInt(enemyTypes.length);
        enemyList.add(new Enemy(enemyTypes[enemyChosen].getTexture(), enemyTypes[enemyChosen].getStartTile(), enemyTypes[enemyChosen].getTileGrid(), 64,64, enemyTypes[enemyChosen].getSpeed(), enemyTypes[enemyChosen].getHealth()));
        enemySpawned++;

    }

    public boolean isCompleted(){
        return waveCompleted;
    }

    public CopyOnWriteArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
}
