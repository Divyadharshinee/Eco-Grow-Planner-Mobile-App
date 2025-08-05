package com.example.ecogrowplanner.ui.diagnose;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecogrowplanner.R;

public class CultivationTipsActivity extends AppCompatActivity {

    private TextView txtTipTitle, txtTipDescription;
    private ImageView imgTip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivation_tips);

        txtTipTitle = findViewById(R.id.txt_tip_title);
        txtTipDescription = findViewById(R.id.txt_tip_description);
        imgTip = findViewById(R.id.img_tip);

        // Get the tip name from intent
        String tipName = getIntent().getStringExtra("TIP_NAME");

        // Set data based on the tip selected
        if (tipName != null) {
            switch (tipName) {
                case "Soil Preparation":
                    imgTip.setImageResource(R.drawable.soil);
                    txtTipTitle.setText("Soil Preparation");
                    txtTipDescription.setText("Prepare your soil by adding organic matter, aerating it, and maintaining the right pH balance to promote plant growth.Steps involved in the Preparation of the Soil\n" +
                            "The first step before preparing the soil is to send it to a lab to determine whether the soil in a particular land or area is suitable for agriculture or not. Before ploughing, the dry land should be watered. Irrigating a land helps loosen the roots of unwanted plants, which can then be easily removed during soil preparation.\n" +
                            "\n" +
                            "Soil Ploughing\n" +
                            "\n" +
                            "Loosening and turning the soil is done through ploughing (tilling). Before sowing the seeds, it is necessary to loosen and turn the soil in the fields in order to break it down to the size of the grains, which is accomplished with the help of three main ploughing implements or tools: hoe, cultivator, and plough.\n" +
                            "Ploughing also brings nutrient-rich soil to the surface. Ploughing can also be used to integrate manure, uproot weeds, remove infectious pathogens and insects, and so on. Ploughs made of wood or iron are used for this.\n" +
                            "tractors or bullocks pull this plough. A hoe is another tool for removing weeds and loose soil. After ploughing, the soil is evenly distributed and levelled in the field.\n" +
                            "Ploughing advantages\n" +
                            "\n" +
                            "Ploughing loosens the soil, allowing nutrients from deep soil to rise to the surface.\n" +
                            "Ploughs made of wood or iron are used for this. tractors or bullocks pull this plough.\n" +
                            "By enabling more air to enter the soil and facilitating easier root penetration, soil aeration will increase.\n" +
                            "Another tool for removing weeds and loosening the soil is the hoe.\n" +
                            "Ploughing can also be used to integrate manure, uproot weeds, remove infectious pathogens and insects, and so on.\n" +
                            "Soil Leveling\n" +
                            "\n" +
                            "Following ploughing, the soil must be levelled. Crumbs are large lumps of soil that may be found in a ploughed field. The soil lumps must be broken up with a plank or an iron leveller. The field has been levelled in preparation for seeding and irrigation.\n" +
                            "\n" +
                            "Farmers used ox-drawn scrapers to level the land in the past, but now a laser land leveller is used to ensure that the surface of the field is even and flat. The laser-guided levellers save time, increase productivity, and conserve water (by reducing water-logging and run-off issues).\n" +
                            "\n" +
                            "Soil levelling advantages\n" +
                            "\n" +
                            "Ploughing ploughed fields keeps the top fertile soil from being carried away by strong winds or washed away by rainwater.\n" +
                            "The levelling of ploughed soil in the field is accomplished with the use of a Leveller. It is either a heavy wooden or iron plank.\n" +
                            "Ploughed fields that have been levelled aid in the uniform distribution of water in the fields during irrigation.\n" +
                            "The levelling of ploughed fields aids in the prevention of moisture loss.\n" +
                            "Soil manuring\n" +
                            "\n" +
                            "Manuring is the final step in soil preparation after ploughing and manuring. It aids in the replenishment of rich nutrients to the soil; nitrogen, phosphorus, and potassium are considered the major nutrients, and manuring ensures that they are added to the soil to increase productivity.\n" +
                            "Manuring also provides many other nutrients and organic fertilisers. The regular addition of compost and other manure improves soil structure, moisture-holding capacity, soil aeration, and water infiltration.\n" );
                    break;

                case "Crop Rotation":
                    imgTip.setImageResource(R.drawable.irrigation);
                    txtTipTitle.setText("Crop Rotation");
                    txtTipDescription.setText("Crop rotation is implemented through a systematic and planned sequence of different crops in the same field over a defined period. The specific implementation can vary based on factors such as climate, soil type, crop types, and the goals of the farmer.\n" +
                            "\n" +
                            "Field Assessment: Firstly, farmers assess the characteristics of their fields, including soil type, fertility levels, drainage, and topography. Then they identify any existing pest or disease issues in the field.\n" +
                            "Crop Selection: Farmers then choose a rotation sequence based on the specific needs and characteristics of the crops and the field. This is done by considering the nutrient requirements, growth habits, and disease susceptibility of different crops.\n" +
                            "Planning the Rotation: Farmers then plan the rotation schedule, typically over a multi-year cycle. Then they decide on the order and duration of each crop in the sequence. And lastly, consider the inclusion of cover crops and green manure in the rotation to enhance soil fertility.\n" +
                            "Diversification: Farmers include crops from different botanical families to disrupt the life cycles of pests and diseases that are specific to certain crop families. Then they aim for a diverse mix of crops to improve overall soil health and reduce the risk of nutrient imbalances.\n" +
                            "Sowing and Harvesting: Farmers follow the planned rotation schedule for sowing each crop. They also pay attention to recommended planting and harvesting times for each crop in the sequence. They also implement proper agronomic practices for each crop, such as irrigation, fertilization, and pest control.\n" +
                            "Cover Crops and Green Manure: Farmers integrate cover crops or green manure into the rotation to provide additional benefits, such as weed suppression, nitrogen fixation, and organic matter improvement. They choose cover crops that contribute to the overall goals of the rotation.\n" +
                            "Record Keeping: Farmers keep detailed records of the crops planted, yields, inputs used, and any observed issues. Record keeping aids in analyzing the success of the rotation and helps plan for future cycles.\n" +
                            "Continuous Improvement: Farmers use the knowledge gained from each rotation cycle to make informed decisions for continuous improvement. They also adapt the rotation plan based on the long-term goals of sustainable agriculture.");
                    break;

                case "Composting":
                    imgTip.setImageResource(R.drawable.composting);
                    txtTipTitle.setText("Composting");
                    txtTipDescription.setText("Composting provides essential nutrients for plants, improves soil structure, and promotes healthy root growth.How to Make Compost\n" +
                            "Add Green Material\n" +
                            "Green material is high in nitrogen. It includes kitchen scraps such as coffee grounds, peelings, fruit cores, uneaten leftovers, and eggshells. Any kitchen waste that is not greasy, dairy, or meat can be composted.\n" +
                            "\n" +
                            "Grass clippings, leaves, and weeds are also considered green materials, as is manure from barnyard animals (herbivores such as cattle or horses, but not cats or dogs).\n" +
                            "\n" +
                            "\"Green\" materials to add to a compost pile\n" +
                            "\n" +
                            "Add Brown Material\n" +
                            "Brown material is high in carbon. Paper, cornstalks, sawdust, small branches, twigs, and straw all fall into this category.\n" +
                            "\n" +
                            "The ratio of nitrogen to carbon should ideally be 50/50 in your compost pile so for every bit of brown material you add, be sure to balance it with green material.\n" +
                            "\n" +
                            "Tip\n" +
                            "If you add paper, such as newspaper, to your compost pile, shred it first so that oxygen can get at a significant amount of the surface. If you don't take this step, you risk it turning moldy and ruining your compost.\n" +
                            "\n" +
                            "\"Brown\" materials for a compost pile\n" +
                            "\n" +
                            "Add Water\n" +
                            "Water is the final key ingredient in a thriving compost pile. Without moisture, your pile will take months to do anything and, if dry enough, will not break down at all. If your pile is too wet, on the other hand, it will smell and become slimy as the ratio of harmful bacteria outweighs the good.\n" +
                            "\n" +
                            "You want the pile to remain damp but not dripping wet. If you do not get enough rainfall to suffice, dump a bucket of water over it once a week to keep things moving.\n" +
                            "\n" +
                            "You will know that your compost pile is right if it becomes hot in the middle. Maintaining heat is important to sterilize the compost and kill the weed seeds or harmful bacteria that may be there. The heat is your proof that the ratio is working for your compost pile.\n" +
                            "\n" +
                            "Watering the compost pile \n" +
                            "\n" +
                            "Turn the Pile Regularly\n" +
                            "Whether using a compost bin or a simple pile, you will need to turn your materials with a shovel or pitchfork. Simply move the outer portion of the pile toward the center, turning each scoop over as you go. Continue shuffling the materials until you have exposed the decomposing materials within the pile.\n" +
                            "\n" +
                            "A compost pile needs to be turned every two to four weeks. If you have a bin with a crank, give it a few turns every week.\n" +
                            "\n" +
                            "If your pile heats up, is adequately moist, and gets turned regularly, you should have usable compost in one to two months.\n" +
                            "\n" +
                            "Compost pile being turned with garden shovel behind chicken wire fence\n" +
                            "Harvest the Compost\n" +
                            "When most of the contents have broken down (this will happen to the bottom layer first), the compost is mature and ready to harvest. Tilt or wiggle the bin to loosen the compost and shovel out the compost that's ready, formed, and broken down fully. Turn the remaining compost and allow it to continue to break down.\n" +
                            "\n" +
                            "Compost poured on metal screen to sift and harvest ready compost \n" +
                            "Use the Harvested Compost\n" +
                            "Harvested compost can be used in many ways. In addition to working it into garden beds, you can also use it as mulch or add it to potting soil. If using it in garden beds, you can sprinkle it on top of the soil or rake it into the soil.\n" +
                            "\n");
                    break;

                case "Germination Tips":
                imgTip.setImageResource(R.drawable.germination);
                txtTipTitle.setText("Seed Starting & Germination Tips");
                txtTipDescription.setText("Starting seeds properly ensures strong, healthy plants! Here are **Seed Starting & Germination Tips to help you grow successfully from seeds. \uD83C\uDF31✨  \n" +
                        "\n" +
                        "  \uD83C\uDF31 Choosing the Right Seeds  \n" +
                        "✔\uFE0F Buy high-quality seeds from reputable sources to ensure good germination rates.  \n" +
                        "✔\uFE0F Check seed packets for expiration dates and germination percentages.  \n" +
                        "✔\uFE0F Choose disease-resistant varieties for better success.  \n" +
                        "\n" +
                        "\n" +
                        "  \uD83C\uDF3F Preparing the Seed Starting Mix \n" +
                        "\uD83C\uDF3E Use a light, well-draining mix for best results. Avoid regular garden soil—it’s too heavy!  \n" +
                        "✅ Best seed-starting mixes include:  \n" +
                        "-  Peat moss + perlite + vermiculite (Holds moisture & aerates roots)  \n" +
                        "- Coconut coir + compost (Eco-friendly & nutrient-rich)  \n" +
                        "- Sterilized potting mix (Prevents fungal diseases)  \n" +
                        "\n" +
                        "\n" +
                        "  \uD83D\uDEE0\uFE0F Best Containers for Seed Starting  \n" +
                        "✔\uFE0F Seed trays with drainage holes – Help prevent waterlogging.  \n" +
                        "✔\uFE0F Peat pots or biodegradable cups – Can be planted directly in the soil.  \n" +
                        "✔\uFE0F Egg cartons or recycled containers – Eco-friendly and budget-friendly.  \n" +
                        "\n" +
                        "\n" +
                        "  \uD83C\uDF1E Light & Temperature Requirements  \n" +
                        "✔\uFE0F Keep seeds warm – Most seeds germinate best at 65-75°F (18-24°C).  \n" +
                        "✔\uFE0F Use a heating mat – Speeds up germination for warmth-loving plants like tomatoes & peppers.  \n" +
                        "✔\uFE0F Provide 12-16 hours of light – Use grow lights or a sunny windowsill.  \n" +
                        "✔\uFE0F Keep lights close to seedlings – About 2-3 inches away to prevent leggy growth.  \n" +
                        "\n" +
                        "\n" +
                        "   \uD83D\uDCA6 Watering & Moisture Tip \n" +
                        "\uD83C\uDF0A Keep soil moist, not soggy – Use a spray bottle for gentle watering.  \n" +
                        "\uD83C\uDF3F Cover trays with plastic wrap – Creates a mini greenhouse to trap moisture.  \n" +
                        "\uD83D\uDEB0 Use bottom watering – Place trays in a shallow water tray to prevent disturbing seeds.  \n" +
                        "\n" +
                        "\n" +
                        "   \uD83D\uDCC5 Seed Planting Depth & Spacing \n" +
                        "\uD83C\uDF31 Rule of thumb: Plant seeds 2-3 times as deep as their diameter.  \n" +
                        "✔\uFE0F Small seeds (lettuce, basil) – Sprinkle on top & lightly cover with soil.  \n" +
                        "✔\uFE0F Medium seeds (tomatoes, peppers) – Plant ¼ to ½ inch deep.  \n" +
                        "✔\uFE0F Large seeds (beans, squash) – Plant 1 inch deep.  \n" +
                        "\n" +
                        "\uD83C\uDF3F Thin seedlings – Once they sprout, remove weaker ones to prevent overcrowding.  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "   \uD83D\uDD04 Transplanting Seedlings\n" +
                        " \n" +
                        "✔\uFE0F  Harden off seedlings – Gradually expose them to outdoor conditions over 7-10 days.  \n" +
                        "✔\uFE0F  Transplant when they have 2-3 true leaves – Handle gently to avoid root damage.  \n" +
                        "✔\uFE0F  Plant on a cloudy day – Reduces transplant shock.  \n" +
                        "\n" +
                        "\n" +
                        "   \uD83D\uDEA8 Common Seed Starting Problems & Fixes\n" +
                        " \n" +
                        "❌ Leggy seedlings (too tall & weak) – Not enough light. Move closer to a light source.  \n" +
                        "❌ Damping-off disease (seedlings collapse) – Overwatering & poor air circulation. Use sterile soil & proper drainage.  \n" +
                        "❌ No germination – Soil too cold or seeds are too old. Keep soil warm & use fresh seeds.  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "    \uD83C\uDF31 Best Easy-to-Grow Seeds for Beginners\n" +
                        "\n" +
                        "- \uD83C\uDF3F Basil – Fast-growing and easy!  \n" +
                        "- \uD83E\uDD6C Lettuce – Germinates quickly.  \n" +
                        "- \uD83C\uDF45 Tomatoes – Great for beginners, just keep warm.  \n" +
                        "- \uD83C\uDF3B Sunflowers – Large seeds, easy to plant.  \n" +
                        "- \uD83E\uDD55 Carrots – Direct sow in loose soil.  \n");
                break;

                case "Attracting Pollinators":
                    imgTip.setImageResource(R.drawable.pollinators);
                    txtTipTitle.setText("Attracting Pollinators & Beneficial Insects");
                    txtTipDescription.setText("\uD83D\uDC1D Attracting Pollinators & Beneficial Insects for Plants \uD83C\uDF38\uD83C\uDF3F  \n" +
                            "\n" +
                            "Encouraging pollinators and beneficial insects is essential for a thriving garden! They boost plant growth, increase fruit and vegetable yields, and control pests naturally. Here are some expert tips to attract these helpful creatures to your garden.  \n" +
                            "\n" +
                            "  \uD83C\uDF3C Best Plants to Attract Pollinators \n" +
                            "\n" +
                            "Pollinators like bees, butterflies, hummingbirds, and beetles need nectar and pollen to thrive. Plant a variety of native flowers to provide food throughout the growing season.  \n" +
                            "\n" +
                            "  \uD83C\uDF38 Flowers that Attract Pollinators: \n" +
                            " \n" +
                            "✅ Lavender – Loved by bees and butterflies.  \n" +
                            "✅ Sunflowers – Provide nectar and pollen for bees.  \n" +
                            "✅ Coneflowers (Echinacea) – Attracts butterflies and bees.  \n" +
                            "✅ Marigolds – Excellent for attracting hoverflies and repelling pests.  \n" +
                            "✅ Wildflowers – Native wildflowers attract a variety of beneficial insects.  \n" +
                            "\n" +
                            "  \uD83C\uDF3F Herbs that Pollinators Love: \n" +
                            "\n" +
                            "✅ Basil – Attracts bees when it flowers.  \n" +
                            "✅ Thyme – Produces tiny flowers that bees adore.  \n" +
                            "✅ Mint & Oregano – Attracts bees and butterflies.  \n" +
                            "✅ Rosemary – Great for early-season pollinators.  \n" +
                            "\n" +
                            "\n" +
                            "  \uD83D\uDC1E Best Beneficial Insects & How to Attract Them\n" +
                            " \n" +
                            "Beneficial insects help control pests naturally by feeding on harmful bugs like aphids, mites, and caterpillars.  \n" +
                            "\n" +
                            "  \uD83D\uDC1D Pollinators (Help with Plant Reproduction)\n" +
                            "  \n" +
                            "- Bees – Attracted to bright flowers (blue, purple, yellow).  \n" +
                            "- Butterflies – Love nectar-rich flowers like zinnias and verbena.  \n" +
                            "- Hummingbirds – Prefer tubular flowers like trumpet vines.  \n" +
                            "\n" +
                            "  \uD83E\uDD97 Natural Pest Controllers  \n" +
                            "\n" +
                            "- Ladybugs – Eat aphids, mealybugs, and scale insects. Attract with: Dill, fennel, yarrow.  \n" +
                            "- Lacewings – Eat caterpillars, mites, and aphids. Attract with: Coreopsis, cosmos, sunflowers.  \n" +
                            "- Praying Mantis – Eats grasshoppers, beetles, and flies. Attract with: Tall grass and shrubs.  \n" +
                            "- Hoverflies – Their larvae eat aphids. Attract with: Marigolds, alyssum, chamomile.  \n" +
                            "\n" +
                            " \uD83C\uDFE1 Creating a Pollinator-Friendly Habitat\n" +
                            " \n" +
                            "1\uFE0F⃣ Plant a Variety of Flowers** – Choose different colors, shapes, and bloom times to provide food all year.  \n" +
                            "2\uFE0F⃣ Provide Shelter – Let some plants grow wild or build an insect hotel for beneficial bugs.  \n" +
                            "3\uFE0F⃣ Offer a Water Source – Small shallow dishes with stones help pollinators drink safely.  \n" +
                            "4\uFE0F⃣ Avoid Pesticides – Use organic pest control methods like neem oil and companion planting.  \n" +
                            "5\uFE0F⃣ Plant in Clusters – Large groups of flowers attract more pollinators than scattered plants.  \n" +
                            "\n" +
                            "\n" +
                            "   \uD83D\uDEAB What to Avoid\n" +
                            "\n" +
                            "❌ Using chemical pesticides – Kills both harmful and beneficial insects.  \n" +
                            "❌ Planting only one type of flower – Reduces biodiversity, making your garden less attractive to pollinators.  \n" +
                            "❌ Removing all weeds – Some weeds (like dandelions) provide food for early-season pollinators.  \n" +
                            "\n");
                    break;

                case "Organic Fertilization Techniques":
                    imgTip.setImageResource(R.drawable.organic);
                    txtTipTitle.setText("Organic Fertilization Techniques");
                    txtTipDescription.setText("\uD83C\uDF3F Organic Fertilization Techniques for Healthy Plants \uD83C\uDF31\uD83D\uDC9A  \n" +
                            "\n" +
                            "Organic fertilization improves soil health, promotes plant growth, and avoids harmful chemicals. Here are the best natural fertilizers and techniques to nourish your plants effectively!  \n" +
                            "\n" +
                            "  \uD83C\uDF3E 1. Compost – The Ultimate Organic Fertilizer\n" +
                            " \n" +
                            "✔\uFE0F What it is: Decomposed organic matter like vegetable scraps, leaves, and grass clippings.  \n" +
                            "✔\uFE0F Benefits:  \n" +
                            "   - Provides balanced nutrients (Nitrogen, Phosphorus, Potassium).  \n" +
                            "   - Improves soil structure and moisture retention.  \n" +
                            "   - Encourages beneficial microbes for plant health.  \n" +
                            "✔\uFE0F How to use: Mix into soil before planting or apply as a top dressing around plants.  \n" +
                            "\n" +
                            "\n" +
                            "  \uD83D\uDCA9 2. Animal Manure – Nutrient-Rich Boost\n" +
                            "  \n" +
                            "✔\uFE0F What it is: Aged manure from cows, chickens, rabbits, or horses.  \n" +
                            "✔\uFE0F Benefits: \n" +
                            "   - High in Nitrogen for leafy plant growth.  \n" +
                            "   - Improves soil fertility and structure.  \n" +
                            "✔\uFE0F How to use:  \n" +
                            "   - Aged or composted manure is best (fresh manure is too strong and may burn plants).  \n" +
                            "   - Work it into the soil before planting or use as a mulch.  \n" +
                            "\n" +
                            "\uD83D\uDEAB Avoid dog/cat manure – It may contain harmful pathogens.  \n" +
                            "\n" +
                            "   \uD83C\uDF3F 3. Green Manure & Cover Crops\n" +
                            "  \n" +
                            "✔\uFE0F What it is: Growing certain plants (clover, alfalfa, peas) and plowing them into the soil as fertilizer.  \n" +
                            "✔\uFE0F Benefits:  \n" +
                            "   - Fixes nitrogen in the soil.  \n" +
                            "   - Prevents soil erosion and improves soil texture.  \n" +
                            "✔\uFE0F How to use: Plant cover crops in the off-season, then cut and mix them into the soil before planting.  \n" +
                            "\n" +
                            "\n" +
                            "  \uD83D\uDC1F 4. Fish Emulsion & Fish Meal – Fast Nutrient Boost\n" +
                            "\n" +
                            "✔\uFE0F What it is: Liquid fertilizer made from fish waste.  \n" +
                            "✔\uFE0F Benefits: \n" +
                            "   - High in nitrogen – great for leafy greens.  \n" +
                            "   - Boosts plant growth quickly.  \n" +
                            "✔\uFE0F How to use: \n" +
                            "   - Dilute fish emulsion with water (1:5 ratio) and apply as a liquid feed.  \n" +
                            "   - Mix fish meal into the soil for slow-release nutrients.  \n" +
                            "\n" +
                            "\n" +
                            "\uD83E\uDD5A 5. Eggshells – Calcium for Strong Plants\n" +
                            "  \n" +
                            "✔\uFE0F What it is: Crushed eggshells added to soil.  \n" +
                            "✔\uFE0F Benefits:  \n" +
                            "   - Provides calcium, preventing blossom-end rot in tomatoes and peppers.  \n" +
                            "   - Improves root growth and cell development.  \n" +
                            "✔\uFE0F How to use: Crush shells and mix them into the soil or sprinkle around plants.  \n" +
                            "\n" +
                            "\n" +
                            "  \uD83C\uDF4C 6. Banana Peels – Potassium & Phosphorus Powerhouse\n" +
                            " \n" +
                            "✔\uFE0F What it is: Decomposing banana peels in soil.  \n" +
                            "✔\uFE0F Benefits:  \n" +
                            "   - Rich in potassium – promotes flowering and fruiting.  \n" +
                            "   - Encourages strong root development.  \n" +
                            "✔\uFE0F How to use:  \n" +
                            "   - Bury small banana peel pieces around plants.  \n" +
                            "   - Soak peels in water for a natural potassium-rich liquid fertilizer.  \n" +
                            "\n" +
                            "\n" +
                            "\uD83C\uDF30 7. Bone Meal & Blood Meal – Slow-Release Nutrients\n" +
                            " \n" +
                            "✔\uFE0F Bone Meal (Phosphorus): Great for root development in flowering and fruiting plants.  \n" +
                            "✔\uFE0F Blood Meal (Nitrogen): Boosts leafy green growth quickly.  \n" +
                            "✔\uFE0F How to use: Sprinkle around plants or mix into the soil before planting.  \n" +
                            "\n" +
                            "\n" +
                            "   \uD83C\uDF75 8. Used Coffee Grounds – Nitrogen Boost\n" +
                            " \n" +
                            "✔\uFE0F What it is: Leftover coffee grounds mixed into the soil.  \n" +
                            "✔\uFE0F Benefits:  \n" +
                            "   - Adds nitrogen for healthy, leafy plant growth.  \n" +
                            "   - Improves soil drainage and aeration.  \n" +
                            "✔\uFE0F How to use: Sprinkle around plants or mix into compost.  \n" +
                            "\n" +
                            "\uD83D\uDEAB Avoid overuse – Too much can make soil acidic.  \n" +
                            "\n" +
                            "  \uD83D\uDC1A 9. Seaweed & Kelp Fertilizer – Micronutrient Boost\n" +
                            "  \n" +
                            "✔\uFE0F What it is: Dried seaweed or kelp used as a soil amendment.  \n" +
                            "✔\uFE0F Benefits:  \n" +
                            "   - Improves plant resilience to stress.  \n" +
                            "   - Provides trace minerals missing in regular fertilizers.  \n" +
                            "✔\uFE0F How to use: \n" +
                            "   - Soak dried seaweed in water to make liquid seaweed fertilizer.  \n" +
                            "   - Apply as a foliar spray or mix into the soil.  \n" +
                            "\n" +
                            " \uD83D\uDEE0\uFE0F Tips for Using Organic Fertilizers Effectively\n" +
                            " \n" +
                            "✔\uFE0F Test your soil before applying fertilizers to avoid nutrient imbalances.  \n" +
                            "✔\uFE0F Apply fertilizers in the morning or evening to reduce evaporation and nutrient loss.  \n" +
                            "✔\uFE0F Water after fertilizing to help nutrients reach plant roots.  \n" +
                            "✔\uFE0F Rotate fertilizers to prevent nutrient deficiencies.  \n" +
                            "\n");
                    break;


                default:
                    txtTipTitle.setText("Unknown Tip");
                    txtTipDescription.setText("No description available.");
                    break;
            }
        }
    }
}


