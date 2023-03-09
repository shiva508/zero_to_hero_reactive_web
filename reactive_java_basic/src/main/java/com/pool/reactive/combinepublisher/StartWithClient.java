package com.pool.reactive.combinepublisher;

import com.pool.Util;
import com.pool.reactive.combinepublisher.helper.NameGenerator;

public class StartWithClient {
    public static void main(String[] args) {
        NameGenerator nameGenerator = new NameGenerator();
        nameGenerator.generateName().take(2).subscribe(Util.subscriber("Batman"));
        nameGenerator.generateName().take(2).subscribe(Util.subscriber("Superman"));
        nameGenerator.generateName().take(3).subscribe(Util.subscriber("Aurther"));
        nameGenerator.generateName().filter(name -> name.startsWith("A")).take(1)
                .subscribe(Util.subscriber("Wonder Woman"));
    }
}
