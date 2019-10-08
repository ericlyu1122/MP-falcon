package ca.ubc.ece.cpen221.mp1;

import java.util.*;

import ca.ubc.ece.cpen221.mp1.utils.*;

public class SoundWaveSimilarity {

    /**
     * Find the sound waves that w is most similar to from the waves in comparisonSet.
     *
     * @param comparisonSet is not null,
     *                      and is set of waves that we will organize in
     *                      similarity groups to identify the group that w
     *                      belongs to. This set should contain w.
     * @param numGroups     is between 1 and the size of the comparisonSet, and
     *                      represents the number of groups to partition the
     *                      set of waves into.
     * @param w             is not null and is included in comparisonSet.
     * @return the set of waves that are in the same group as w after grouping.
     */
    public  Set<SoundWave> getSimilarWaves(Set<SoundWave> comparisonSet, int numGroups, SoundWave w)  {

        List<SoundWave> soundSet= new ArrayList<>();
        soundSet.addAll(comparisonSet);

        Map<Pair<SoundWave>, Double> soundPair;

        Groups<SoundWave> AllGroup=new Groups<>();
        AllGroup=sortgroup((ArrayList<SoundWave>) soundSet);

        soundPair=toGetAllSim(soundSet);

        int n=comparisonSet.size();
        double maxSim;

        List<Pair<SoundWave>> allPair=new ArrayList<>();
        allPair.addAll(soundPair.keySet());

        while(n>numGroups){
            maxSim=0;
            Pair<SoundWave> findMax=new Pair<>(soundSet.get(0),soundSet.get(1));

            for(Pair pair: allPair){
                if(soundPair.get(pair)>=maxSim){
                    maxSim=soundPair.get(pair);
                    findMax=pair;
                }
            }

            allPair.remove(findMax);
            soundPair.remove(findMax);
            /* n-- when merge
            * */
            SoundWave h1=AllGroup.find(findMax.getElem1());
            SoundWave h2=AllGroup.find(findMax.getElem2());
            if(h1!=h2){
                n--;
                AllGroup.merge(h1,h2);
            }

        }
        Set<SoundWave> wGroupSet=new HashSet<SoundWave>();

        for (SoundWave soundWave :comparisonSet) {
            if (AllGroup.find((SoundWave) soundWave) == AllGroup.find((SoundWave) w)) {
                wGroupSet.add(soundWave);
            }
        }

        return wGroupSet; // change this!
    }

    private Groups<SoundWave> sortgroup(ArrayList<SoundWave> comp){
        Groups<SoundWave> sor=new Groups<>();
        for(SoundWave www:comp){
            sor.add(www);
        }
        return sor;
    }
    /**
     *
     * @param   soundset: a List of SoundWave.
     * @return  a Map: Mapping the pair of SoundWaves with their similarity
     * *
     * */
    public Map<Pair<SoundWave>, Double> toGetAllSim(List<SoundWave> soundset){
            Map<Pair<SoundWave>, Double> mapPair =new HashMap<>();
            double sim;
            for (int i=0;i<soundset.size();i++){
                for(int j=soundset.size()-1;j>i;j--){
                   Pair<SoundWave> p=new Pair<>(soundset.get(i),soundset.get(j));
                   sim=soundset.get(i).similarity(soundset.get(j));
                   mapPair.put(p,sim);
                }
            }
            return mapPair;
    }
}



