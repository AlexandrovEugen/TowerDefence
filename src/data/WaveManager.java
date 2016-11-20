package data;


public class  WaveManager {
    private float  timeSinceLastWawe, timeBetweenEnemies;
    private int  waveNumber, enemiesPerWave;
    private Enemy[] enemyTypes;
    private Wave currentWave;



    public WaveManager(Enemy[] enemyTypes, int enemiesPerWave, float timeBetweenEnemies) {
        this.enemiesPerWave = enemiesPerWave;
        this.enemyTypes = enemyTypes;
        this.timeSinceLastWawe = 0;
        this.waveNumber = 0;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.currentWave = null;

        newWave();
    }

    public void update(){
        if (!currentWave.isCompleted()){
            currentWave.update();
        }
        else
            newWave();

    }
    private void newWave(){
        currentWave = new Wave(enemyTypes, timeBetweenEnemies, enemiesPerWave);
        waveNumber++;
        System.out.println("Beginning Wave" + waveNumber);
    }
    public Wave getCurrentWave(){
        return currentWave;
    }

    public int getWaveNumber(){
        return waveNumber;
    }
}
