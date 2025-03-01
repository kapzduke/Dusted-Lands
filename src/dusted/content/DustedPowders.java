package dusted.content;

import arc.graphics.*;
import dusted.type.*;

public class DustedPowders {
    //TODO i need more powders
    public static Powder quartzDust, orchar, sulfur, cocaine;

    public static void load() {
        //TODO maybe rename this to silica
        quartzDust = new Powder("quartz-dust", Color.valueOf("ffe8ee")) {{
            temperature = 0.5f;
        }};

        orchar = new Powder("orchar", Color.valueOf("7d3624")) {{
            flammability = 1f;
            temperature = 0.3f;
        }};

        sulfur = new Powder("sulfur", Color.valueOf("f7e89c")) {{
            flammability = 0.4f;
            explosiveness = 0.6f;
        }};

        cocaine = new Powder("cocaine", Color.valueOf("e6e6e6")) {{
            effect = DustedStatusEffects.high;
        }};
    }
}
