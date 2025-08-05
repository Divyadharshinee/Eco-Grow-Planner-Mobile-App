package com.example.ecogrowplanner.ui.diagnose;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecogrowplanner.R;

public class PlantCareTipsActivity extends AppCompatActivity {

    private TextView txtTipTitle, txtTipDescription;
    private ImageView imgTip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_tips);

        txtTipTitle = findViewById(R.id.txt_tip_title);
        txtTipDescription = findViewById(R.id.txt_tip_description);
        imgTip = findViewById(R.id.img_tip);

        // Get the tip name from intent
        String tipName = getIntent().getStringExtra("TIP_NAME");

        // Set data based on the tip selected
        if (tipName != null) {
            switch (tipName) {
                case "Watering Tips":
                    imgTip.setImageResource(R.drawable.water);
                    txtTipTitle.setText("Watering Tips");
                    txtTipDescription.setText("Keeping your plants properly watered is key to keeping them healthy! Here are some solid watering tips:  \n" +
                            "\n" +
                            " General Watering Tips  \n" +
                            "\uD83C\uDF31 Check the soil first – Stick your finger about an inch into the soil; if it feels dry, it’s time to water. If it’s still damp, wait a day or two.  \n" +
                            "\uD83D\uDCA6 Water deeply, not just the surface – Shallow watering leads to weak roots. Aim to soak the roots so they grow deeper and stronger.  \n" +
                            "\uD83D\uDD70\uFE0F Water in the morning – This helps plants absorb moisture before the heat of the day evaporates it. Evening watering is okay too, but avoid wetting leaves overnight to prevent mold.  \n" +
                            "\uD83D\uDEBF Use room-temperature water – Cold water can shock plant roots, while hot water can damage them.  \n" +
                            "\uD83C\uDF24\uFE0F Adjust for seasons – Plants need more water in hot weather and less in winter when growth slows down.  \n" +
                            "\n" +
                            " Indoor Plant Tips\n" +
                            "\uD83C\uDFE0 Use pots with drainage holes – Prevents water from sitting at the bottom and causing root rot.  \n" +
                            "☁\uFE0F Watch for humidity levels– Some plants (like ferns) love extra moisture, so misting can help. Others (like succulents) prefer dry conditions.  \n" +
                            "\uD83D\uDD04 Be consistent – Try to water on a schedule, but always check soil moisture first.  \n" +
                            "\n" +
                            " Outdoor Plant Tips\n" +
                            "\uD83C\uDF1E Water early or late – Reduces evaporation and helps plants stay hydrated longer.  \n" +
                            "\uD83C\uDF31 Mulch helps retain moisture – A layer of mulch around plants keeps the soil from drying out too quickly.  \n" +
                            "\uD83D\uDCA7 Drip irrigation is great– If you have many plants, a slow-drip system or soaker hose conserves water and keeps roots moist.  \n" +
                            "\n");
                    break;

                case "Fertilizing Tips":
                    imgTip.setImageResource(R.drawable.fertilizer);
                    txtTipTitle.setText("Fertilizing Tips");
                    txtTipDescription.setText("Fertilizing your plants properly helps them grow strong and healthy! Here are some essential tips to keep in mind:  \n" +
                            "\n" +
                            "General Fertilizing Tips \n" +
                            "\uD83C\uDF31 Know your plant's needs – Different plants need different nutrients. Leafy greens love nitrogen, while flowering plants need more phosphorus.  \n" +
                            "\uD83D\uDCC5 Follow a schedule – Most plants do well with fertilizer every 2-4 weeks during the growing season (spring and summer). Cut back in fall and winter.  \n" +
                            "\uD83D\uDCA7 Water before fertilizing – Applying fertilizer to dry soil can burn roots. Always water your plants first.  \n" +
                            "\uD83E\uDD44 Less is more – Over-fertilizing can harm plants. Always follow the recommended amount on the package.  \n" +
                            "\n" +
                            "Indoor Plant Fertilizing\n" +
                            "\uD83C\uDFE0 Use liquid fertilize – Easy to mix into watering routines. A balanced 10-10-10 or 20-20-20 formula works well for most houseplants.  \n" +
                            "\uD83C\uDF3F Feed only during active growth – Skip fertilizing in winter when indoor plants are dormant.  \n" +
                            "\uD83D\uDEAB Avoid fertilizer buildup – Flush soil with plain water occasionally to prevent salt buildup.  \n" +
                            "\n" +
                            " Outdoor Plant Fertilizing \n" +
                            "\uD83C\uDF1E Use organic fertilizers – Compost, manure, or fish emulsion provide nutrients naturally and improve soil health.  \n" +
                            "\uD83C\uDF3E Slow-release fertilizers work best – They provide a steady supply of nutrients over time.  \n" +
                            "\uD83C\uDF3C Flowering plants need phosphorus – Use a fertilizer higher in phosphorus (middle number, e.g., 10-30-10) to encourage blooming.  \n" +
                            "\uD83E\uDD55 Vegetables need regular feeding – Heavy feeders like tomatoes and peppers benefit from consistent feeding every 2-3 weeks.  \n" +
                            "\n" +
                            " Signs Your Plant Needs Fertilizer\n" +
                            "\uD83D\uDEA8 Yellowing leaves – Could indicate nitrogen deficiency.  \n" +
                            "\uD83E\uDD40 Slow growth or small leaves – A sign of lacking nutrients.  \n" +
                            "\uD83C\uDF38 No flowers or fruit – May need more phosphorus and potassium.  \n" +
                            "\n");
                    break;

                case "Sunlight Tips":
                    imgTip.setImageResource(R.drawable.sun);
                    txtTipTitle.setText("Sunlight Tips");
                    txtTipDescription.setText("Getting the right amount of sunlight is crucial for healthy plant growth! Here are some key sunlight tips:  \n" +
                            "\n" +
                            " General Sunlight Tips\n" +
                            "☀\uFE0F Know your plant’s light needs – Some plants thrive in full sun, while others prefer shade. Check your plant’s specific requirements.  \n" +
                            "⏳ Gradually adjust to more light – If moving a plant to a sunnier spot, do it slowly over a few days to prevent sunburn.  \n" +
                            "\uD83D\uDD75\uFE0F Observe how sunlight moves – Light changes throughout the day, so place plants accordingly. Morning sun is gentler than afternoon sun.  \n" +
                            "\n" +
                            " Indoor Plant Sunlight Tips \n" +
                            "\uD83C\uDFE1 Rotate your plants – Turn them every few weeks to ensure even growth and prevent leaning.  \n" +
                            "\uD83C\uDF24\uFE0F Use grow lights if needed – If your home lacks natural light, LED grow lights can help.  \n" +
                            "\uD83D\uDEAA Watch for drafts & windows – Some plants don’t like direct sun through glass, which can cause leaf burn.  \n" +
                            "\n" +
                            " Outdoor Plant Sunlight Tips \n" +
                            "\uD83C\uDF1E Full sun plants need at least 6 hours of direct sunlight – Examples: tomatoes, roses, and lavender.  \n" +
                            "\uD83C\uDF3F Partial sun plants need 3-6 hours – Examples: ferns, begonias, and impatiens.  \n" +
                            "\uD83C\uDF33 Shade-loving plants thrive with less than 3 hours of sun – Example:snake plants.  \n" +
                            "\uD83D\uDCA6 Water more in direct sun – Plants in full sun dry out faster and need more frequent watering.  \n" +
                            "\n" +
                            " Signs of Too Much or Too Little Sunlight \n" +
                            "\uD83D\uDEA8 Too much sun – Leaves may turn brown, dry, or look scorched. Move to partial shade if needed.  \n" +
                            "\uD83C\uDF11 Too little sun – Slow growth, pale leaves, or leggy stems indicate a need for more light.  \n" +
                            "\n");
                    break;

                case "Soil Tips":
                    imgTip.setImageResource(R.drawable.soiltips);
                    txtTipTitle.setText("Soil Tips");
                    txtTipDescription.setText("Healthy soil is the foundation for strong and thriving plants! Here are some essential soil care tips to help your plants grow better:  \n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            " \uD83D\uDEE0\uFE0F Choosing the Right Soil  \n" +
                            "\uD83C\uDF31 Know your plant’s soil needs– Different plants need different types of soil:  \n" +
                            "- Succulents & Cacti → Well-draining, sandy soil.  \n" +
                            "- Vegetables & Herbs → Loamy, nutrient-rich soil.  \n" +
                            "- Flowering plants → Moist but well-drained soil.  \n" +
                            "- Indoor plants → Light, airy potting mix with good drainage.  \n" +
                            "\n" +
                            "\uD83C\uDF3F Use the right pH balance – Most plants thrive in slightly acidic to neutral soil (pH 6.0-7.0). Some, like blueberries, prefer acidic soil (pH 4.5-5.5).  \n" +
                            "\n" +
                            "\n" +
                            " \uD83D\uDCA7 Drainage & Aeration Tips\n" +
                            "\uD83D\uDD73\uFE0F Ensure good drainage – Use pots with drainage holes and avoid compacting the soil too much.  \n" +
                            "\uD83D\uDD04 Loosen soil regularly – Over time, soil gets compacted, making it hard for roots to grow. Use a small rake or fork to aerate it.  \n" +
                            "\uD83D\uDED6 Add perlite or sand – Helps improve drainage, especially for succulents and plants that don’t like too much moisture.  \n" +
                            "\n" +
                            "\n" +
                            " \uD83C\uDF7D\uFE0F Nutrient-Rich Soil Tips  \n" +
                            "\uD83D\uDCA9 Use compost or organic matter – Mix in compost, aged manure, or worm castings to enrich the soil with nutrients.  \n" +
                            "⚡ Rotate soil for vegetables – If growing veggies, rotate crops each season to prevent nutrient depletion and soil-borne diseases.  \n" +
                            "\uD83E\uDD44 Use slow-release fertilizers – This provides steady nutrients over time without overwhelming the plant.  \n" +
                            "\n" +
                            "\n" +
                            "  \uD83D\uDC1B Healthy Microorganisms & Pest Control  \n" +
                            "\uD83C\uDF3E Encourage beneficial microbes – Adding compost, organic mulch, or mycorrhizal fungi helps soil microbes break down nutrients for plants.  \n" +
                            "\uD83D\uDC1C Keep pests away naturally – Cinnamon, neem oil, and diatomaceous earth can help prevent soil-borne pests.  \n" +
                            "\uD83D\uDEA8 Check for soil diseases – If plants show yellowing leaves or stunted growth, test for fungal infections or nutrient imbalances.  \n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "  \uD83E\uDEB4 Indoor Plant Soil Tips \n" +
                            "\uD83C\uDF31 Replace old soil every 1-2 years – Over time, indoor potting soil loses nutrients and can compact.  \n" +
                            "\uD83D\uDCA6 Don’t let soil stay too wet – Use a well-draining potting mix and avoid overwatering to prevent root rot.  \n" +
                            "\uD83C\uDF24\uFE0F Use activated charcoal – Helps absorb excess moisture and prevents mold growth in indoor plant soil.  \n" +
                            "\n");
                    break;

                case "pest control tips":
                    imgTip.setImageResource(R.drawable.pest);
                    txtTipTitle.setText("pest control tips");
                    txtTipDescription.setText("Keeping pests away from your plants is essential for their health! Here are some effective pest control tips to protect your plants naturally and safely.  \n" +
                            "\n" +
                            "\uD83D\uDC1B Common Plant Pests & How to Control Them\n" +
                            "  \n" +
                            "1\uFE0F⃣ Aphids \uD83E\uDEB2 – Tiny green, black, or white bugs that suck sap from plants.  \n" +
                            "   ✅ Solution: Spray with neem oil, insecticidal soap, or a mix of water + dish soap.  \n" +
                            "\n" +
                            "2\uFE0F⃣ Spider Mites\uD83D\uDD77\uFE0F – Microscopic pests that create webbing and turn leaves yellow.  \n" +
                            "   ✅ Solution: Mist plants often (they hate humidity) and wipe leaves with a mix of water + rubbing alcohol.  \n" +
                            "\n" +
                            "3\uFE0F⃣ Mealybugs \uD83E\uDDA0 – White, cotton-like bugs that weaken plants.  \n" +
                            "   ✅ Solution: Dab them with a cotton swab dipped in alcohol or spray neem oil.  \n" +
                            "\n" +
                            "4\uFE0F⃣ Fungus Gnats\uD83E\uDD9F – Small flying insects that lay eggs in moist soil.  \n" +
                            "   ✅ Solution: Let soil dry between waterings, use yellow sticky traps, and add cinnamon to soil (it kills larvae).  \n" +
                            "\n" +
                            "5\uFE0F⃣ Whiteflies \uD83E\uDDA9 – Tiny white winged bugs that gather under leaves.  \n" +
                            "   ✅ Solution: Spray with soapy water or introduce ladybugs (natural predators).  \n" +
                            "\n" +
                            "6\uFE0F⃣ Caterpillars \uD83D\uDC1B – They munch on leaves and stems.  \n" +
                            "   ✅ Solution: Hand-pick them off or use Bacillus thuringiensis (BT), a natural bacteria that targets caterpillars.  \n" +
                            "\n" +
                            "7\uFE0F⃣ Slugs & Snails \uD83D\uDC0C – Leave holes in leaves and slime trails.  \n" +
                            "   ✅ Solution: Use crushed eggshells or coffee grounds around plants to deter them.  \n" +
                            "\n" +
                            "\n" +
                            " \uD83C\uDF3F Natural Pest Control Method  \n" +
                            "\n" +
                            "\uD83C\uDF31 Neem Oil Spray – Works against aphids, mealybugs, spider mites, and more. Safe for plants!  \n" +
                            "\uD83D\uDCA7 Soapy Water Spray – Mix a few drops of dish soap in water and spray affected areas.  \n" +
                            "\uD83E\uDD9F Sticky Traps – Use yellow sticky traps for flying pests like fungus gnats and whiteflies.  \n" +
                            "\uD83D\uDC1E Introduce Beneficial Insects – Ladybugs and lacewings eat aphids and mites.  \n" +
                            "\uD83C\uDF3F Companion Planting – Plant marigolds, basil, and lavender to repel pests naturally.  \n" +
                            "\n" +
                            "  \uD83D\uDEA8 Preventing Pest Problems  \n" +
                            "\n" +
                            "✔\uFE0F Inspect plants regularly – Check under leaves and soil for signs of pests.  \n" +
                            "✔\uFE0F Quarantine new plants – Keep new plants separate for a few days before adding them to your garden or home.  \n" +
                            "✔\uFE0F Improve air circulation – Avoid overcrowding plants to reduce humidity and mold growth.  \n" +
                            "✔\uFE0F Keep leaves clean – Wipe dust off leaves to discourage pests like spider mites.  \n" +
                            "\n");
                    break;

                case "air and humidity tips":
                    imgTip.setImageResource(R.drawable.humidity);
                    txtTipTitle.setText("air and humidity tips");
                    txtTipDescription.setText("Air circulation and humidity play a huge role in plant health! Here are some air and humidity tips to help your plants thrive:  \n" +
                            "\n" +
                            "  \uD83D\uDCA8 Air Circulation Tips \n" +
                            "\n" +
                            "\uD83C\uDF3F Keep plants spaced out – Overcrowding can lead to poor airflow, making plants more prone to mold and pests.  \n" +
                            "\uD83D\uDCA8 Use a fan for better ventilation – A gentle fan indoors can help prevent fungal diseases and keep air fresh.  \n" +
                            "\uD83D\uDEAA Avoid stagnant air – Open windows when possible to let in fresh air, especially for indoor plants.  \n" +
                            "✂\uFE0F Trim overgrown leaves – Pruning dense foliage helps air reach all parts of the plant.  \n" +
                            "\n" +
                            "  \uD83D\uDCA6 Humidity Tips \n" +
                            " \n" +
                            "\uD83C\uDF21\uFE0F Know your plant’s humidity needs – Some plants, like ferns and orchids, love high humidity, while succulents and cacti prefer dry air.  \n" +
                            "\uD83D\uDCA7 Mist humidity-loving plants – Light misting can help plants like calatheas, ferns, and peace lilies stay hydrated.  \n" +
                            "\uD83C\uDF2B\uFE0F Use a humidity tray – Place pebbles in a shallow tray with water, then set your plant pot on top (without touching the water) to increase moisture.  \n" +
                            "\uD83D\uDEC1 Group plants together – Placing several plants close together creates a micro-humid environment.  \n" +
                            "\uD83D\uDCA8 Use a humidifier – If your home has dry air, a humidifier can maintain the right humidity level for tropical plants.  \n" +
                            "\n" +
                            "\n" +
                            "  \uD83D\uDEA8 Signs of Too Much or Too Little Humidity\n" +
                            "\n" +
                            "⚠\uFE0F Too dry – Brown leaf edges, curling leaves, or slow growth (common in ferns, calatheas, and orchids).  \n" +
                            "⚠\uFE0F Too humid – Moldy soil, yellowing leaves, or fungal growth (common if there’s poor air circulation).  \n" +
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

