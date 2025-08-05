package com.example.ecogrowplanner.ui.diagnose;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecogrowplanner.R;

public class CommonProblemsActivity extends AppCompatActivity {

    private TextView txtProblemTitle, txtProblemDescription;
    private ImageView imgProblem;
    private static final String TAG = "CommonProblemsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_problems);

        txtProblemTitle = findViewById(R.id.txt_problem_title);
        txtProblemDescription = findViewById(R.id.txt_problem_description);
        imgProblem = findViewById(R.id.img_problem);

        // Get the problem name from intent
        String problemName = getIntent().getStringExtra("PROBLEM_NAME");

        // Debugging Log
        Log.d(TAG, "Received problemName: " + problemName);

        if (problemName == null) {
            txtProblemTitle.setText("Error: No Problem Name");
            txtProblemDescription.setText("Intent extra is missing.");
            imgProblem.setImageResource(R.drawable.logo); // Default image
            return;
        }

        // Set data based on the problem selected
        switch (problemName) {
            case "Chlorosis":
                imgProblem.setImageResource(R.drawable.chlorosis);
                txtProblemTitle.setText("Chlorosis (Yellowing Leaves)");
                txtProblemDescription.setText("In botany, chlorosis is a condition in which leaves produce insufficient chlorophyll. As chlorophyll is responsible for the green color of leaves, chlorotic leaves are pale, yellow, or yellow-white. The affected plant has little or no ability to manufacture carbohydrates through photosynthesis and may die unless the cause of its chlorophyll insufficiency is treated and this may lead to a plant disease called rusts, although some chlorotic plants, such as the albino Arabidopsis thaliana mutant ppi2, are viable if supplied with exogenous sucrose.[1]\n" +
                        "\n" +
                        "The word chlorosis is derived from the Greek khloros meaning \"greenish-yellow\", \"pale green\", \"pale\", \"pallid\", or \"fresh\".\n" +
                        "\n" +
                        "In viticulture, the most common symptom of poor nutrition in grapevines is the yellowing of grape leaves caused by chlorosis and the subsequent loss of chlorophyll. This is often seen in vineyard soils that are high in limestone such as the Italian wine region of Barolo in the Piedmont, the Spanish wine region of Rioja and the French wine regions of Champagne and Burgundy. In these soils the grapevine often struggles to pull sufficient levels of iron which is a needed component in the production of chlorophyll.[2]\n" +
                        "\n" +
                        "Causes\n" +
                        "\n" +
                        "A Liquidambar leaf with interveinal chlorosis\n" +
                        "\n" +
                        "Lemon shrub with chlorosis\n" +
                        "Chlorosis is typically caused when leaves do not have enough nutrients to synthesise all the chlorophyll they need. It can be brought about by a combination of factors including:\n" +
                        "\n" +
                        "a specific mineral deficiency in the soil, such as iron,[3] magnesium or zinc[4]\n" +
                        "deficient nitrogen and/or proteins[4]\n" +
                        "a soil pH at which minerals become unavailable for absorption by the roots[5]\n" +
                        "poor drainage (waterlogged roots)[5]\n" +
                        "damaged and/or compacted roots[5]\n" +
                        "pesticides and particularly herbicides may cause chlorosis, both to target weeds and occasionally to the crop being treated.[6]\n" +
                        "exposure to sulphur dioxide[7]\n" +
                        "ozone injury to sensitive plants [2]\n" +
                        "presence of any number of bacterial pathogens, for instance Pseudomonas syringae pv. tagetis that causes complete chlorosis on Asteraceae[8]\n" +
                        "fungal infection, e.g. Bakanae.\n" +
                        "However, the exact conditions vary from plant type to plant type. For example, Azaleas grow best in acidic soil and rice is unharmed by waterlogged soil.");
                break;

            case "Brown Tips":
                imgProblem.setImageResource(R.drawable.brown_tips);
                txtProblemTitle.setText("Brown Tips on Leaves");
                txtProblemDescription.setText("Everyone has had at least one plant with those bad-looking brown ending tips. You are not alone, this is indeed one of the most common problems in gardening. We find it unsightly, but on top of that, it makes us worry about what causes brown tips and how we might fix them.\n" +
                        "\n" +
                        "Plants develop brown leaf tips due to 1) underwatering, 2) overwatering, 3) imbalanced nutrients, 4) over-draining soil, 5) compact soil, 6) too little or too much light, 7) overcrowded roots, 8) temperature and humidity, 9) pests and diseases, and 10) aging.\n" +
                        "\n" +
                        "Our instinct is to snip away the damaged foliage to make our plant look better, but this isn’t always the wisest course of action. It is best to figure out what’s causing the brown tips in the first place, then take steps to solve the issue. Let’s look into what brown plant tips are.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "What Are Brown Tips On Plant Leaves?\n" +
                        "The brown leaf tips are dead plant cells, and most often, it indicates that the plant is under stress conditions. Those dead plant cells will not revert once they become brown, but immediate action can help bring the rest of the plant back to life.\n" +
                        "\n" +
                        "Browning on the leaves may develop in different patterns and that will help gardeners determine the probable cause of plant distress. These are the patterns of how plants get brown leaves and what they signify.\n" +
                        "\n" +
                        "Wrinkled brown tips and margins– watering or feeding issues\n" +
                        "Random brown patches, crispy and thin– too much sun, high temperature\n" +
                        "Yellow to mushy brown spots – too much water\n" +
                        "Curling edges, soft brown circles– pests and diseases\n" +
                        "Browning of the entire leaf– aging process\n" +
                        "\n" +
                        "\n");
                break;

            case "Mold and Mildew":
                imgProblem.setImageResource(R.drawable.mold_mildew);
                txtProblemTitle.setText("Mold and Mildew");
                txtProblemDescription.setText(" Mildew is a form of fungus. It is distinguished from its closely related counterpart, mold, largely by its colour: molds appear in shades of black, blue, red, and green, whereas mildew is white. It appears as a thin, superficial growth consisting of minute hyphae (fungal filaments) produced especially on living plants or organic matter such as wood, paper or leather.[1][2] Both mold and mildew produce distinct offensive odours, and both have been identified as the cause of certain human ailments.[citation needed]\n" +
                        "\n" +
                        "In horticulture, mildews are species of fungus in the order Erysiphales, or fungus-like organisms in the family Peronosporaceae. It is also used more generally to mean mold growth. In Old English, mildew meant honeydew (a substance secreted by aphids on leaves, formerly thought to distill from the air like dew), and later came to mean mold or fungus.[3]\n" +
                        "\n" +
                        "Household varieties The term mildew is often used generically to refer to mold growth, usually with a flat growth habit. Molds can thrive on many organic materials, including clothing, leather, paper, and the ceilings, walls and floors of homes or offices with poor moisture control. Mildew can be cleaned using specialized mildew remover, or substances such as bleach (though they may discolour the surface)");

                break;

            case "Root Rot":
            imgProblem.setImageResource(R.drawable.rootrot);
            txtProblemTitle.setText("Root Rot");
            txtProblemDescription.setText(" Symptoms: Wilting, yellowing leaves, mushy black roots.\n" +
                    "Causes:\n" +
                    "✔\uFE0F Overwatering and poor drainage\n" +
                    "✔\uFE0F Fungal infections from damp soil\n" +
                    "✔\uFE0F Pots without drainage holes\n" +
                    "\n" +
                    "How to Fix:\n" +
                    "✅ Repot the plant with fresh, well-draining soil.\n" +
                    "✅ Trim off mushy, black roots.\n" +
                    "✅ Water only when the top 2 inches of soil are dry.");
            break;

            case "Wilting Leaves":
                imgProblem.setImageResource(R.drawable.wilting_leaves);
                txtProblemTitle.setText("Wilting Leaves");
                txtProblemDescription.setText("\uD83D\uDCA7 Symptoms: Drooping leaves that appear lifeless.\n" +
                        "Causes:\n" +
                        "✔\uFE0F Underwatering or overwatering\n" +
                        "✔\uFE0F Root damage or transplant shock\n" +
                        "✔\uFE0F Too much sun or heat stress\n" +
                        "\n" +
                        "How to Fix:\n" +
                        "✅ Check soil moisture – water if the soil is dry 2 inches deep.\n" +
                        "✅ Move to a shaded area if exposed to intense sun.\n" +
                        "✅ Mist leaves to reduce heat stress.");
                break;

            case "Sunburned Leaves":
                imgProblem.setImageResource(R.drawable.sunburned);
                txtProblemTitle.setText("Sunburned Leaves");
                txtProblemDescription.setText("\uD83D\uDD25 Symptoms: Brown, dry patches on leaves.\n" +
                        "Causes:\n" +
                        "✔\uFE0F Too much direct sunlight\n" +
                        "✔\uFE0F Sudden exposure to bright light\n" +
                        "\n" +
                        "How to Fix:\n" +
                        "✅ Move to partial shade if leaves are burning.\n" +
                        "✅ Acclimate plants slowly to direct sun exposure.\n" +
                        "✅ Water deeply in the morning to keep roots hydrated.");
                break;

            default:
                txtProblemTitle.setText("Unknown Problem");
                txtProblemDescription.setText("No description available.");
                imgProblem.setImageResource(R.drawable.logo); // Default image
                break;
        }
    }
}

