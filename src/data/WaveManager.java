package data;

/**
 * Created by Евгений on 06.10.2016.
 */
public class WaveManager {
    private float  timeSinceLastWawe, timeBetweenEnemies;
    private int  waveNumber, enemiesPerWave;
    private Enemy enemyType;
    private Wave currentWave;



    public WaveManager(Enemy enemyType, int enemiesPerWave, float timeBetweenEnemies) {
        this.enemiesPerWave = enemiesPerWave;
        this.enemyType = enemyType;
        this.timeSinceLastWawe = 0;
        this.waveNumber = 0;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.currentWave = null;

        newWave();
    }

    public void update(){
        if (!currentWave.isCompleted()){
            currentWave.Update();
        }
        else
            newWave();

    }
    private void newWave(){
        currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave);
        waveNumber++;
        System.out.println("Beginning Wave" + waveNumber);
    }
    public Wave getCurrentWave(){
        return currentWave;
    }
}
