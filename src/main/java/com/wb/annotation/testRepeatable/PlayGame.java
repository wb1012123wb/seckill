package com.wb.annotation.testRepeatable;

/**
 * 3. People 玩多个 Game
 *
 * @Game 可使用多次，是因为 @Game注解 被 @Repeatable(People.class) 注解标注
 */
@Game(value = "LOL")
@Game(value = "PUBG")
@Game(value = "NFS")
@Game(value = "Dirt4")
public class PlayGame {

    @Game(value = "LOL")
    @Game(value = "PUBG")
    @Game(value = "NFS")
    @Game(value = "Dirt4")
    public void play() {

    }

}