package ke.co.croconet.housemenu;

import android.widget.Toast;

public class foodDescription {

    static String titleStr = "Brief Nutritional Facts Of ", blankTea="-", foodName="", foodNutrition="", desc="";

    static String dengu = "Green grams", rice = "Rice", skuma= "Sukuma", waru="Irish Potato", eggs="Eggs", bread="Bread",
            porridge="Porridge", goat="Goat", ugali="Ugali", bananas="Bananas", chicken="Chicken",
            chapati="Chapati", beans="Beans", snacks="Snacks", beef="Beef",  tea="Tea", fish="Fish",
            githeri="Githeri", cabbage="Cabbage", rabuon="Sweet Potato", blank="", nutritionTitle;


    static String ackee="Ackee fruit", aliya="Dried meat",cucumber="Cucumber",apple="Apple",cherimoya="Custard Apple", nduma="Arrowroot", avocado="Avocado", ripebanana="Ripe banana",matoke="Cooked banana",bhajia="Bhajia",biryani="Biryani",brocolli="Brocolli",
            burger="Burger",carrot="Carrot",cassava="Cassava",chips="Chips",coconutMilk="Coconut milk",salad="Kachumbari",cupuacu="Cupuacu",dates="Dates",
            dragonFruit="Dragon fruit",durian="Durian",eggplant="Eggplant",fermentedMilk="Fermented milk",figs="Figs",gizzard="Gizzards",grilledMeat="Grilled meat",guava="Guava",terere="Terere",arrowrootLeaves="Arrowroot leaves",
            nderema="Nderema",beanLeaves="Bean leaves",managu="Managu",cassavaLeaves="Cassava leaves",kunde="Kunde",mitoo="Miro/mito",mrenda="Mrenda",seveve="Seveve",pumpkin="Pumpkin",saga="Saga",stingingNettleLeaves="Nettle leaves",
            sweetPotatoLeaves="Potato leaves",jabuticaba="Jabuticaba",jackFruit="Jack fruit",juice="Juice",lemon="Lemon",lime="Lime",liver="Liver",mabuyu="Mabuyu",roastedMaize="Roasted maize",boiledMaize="Boiled maize",
            mandazi="Mandazi",mango="Mango",mashedPotato="Mashed potato",mbaazi="Pigeon peas",minji="Minji",miracleFruit="Miracle fruit",mukimo="Mukimo",mushroom="Mushroom",muthokoi="Muthokoi",mutton="Mutton",olive="Olive",
            omena="Omena",orange="Oranges",pawpaw="Pawpaw",peaches="Peaches",peanutStew="Peanut stew",peas="Green peas",pilau="Pilau",pineapple="Pineapple",pizza="Pizza",pomegranate="Pomegranate",pork="Pork",rambutan="Rambutan",samosa="Samosa",
            sausage="sausage",shellfish="Shellfish",smokey="Smokey",soda="Soda", coffee="Coffee", chocolate="Chocolate", lentils="Lentils",spinach="Spinach",tangerine="Tangerine",viaziKarai="Viazi karai",watermelon="Watermelon",wingedTermite="Winged termite",wine="Wine";

    static String carbs="Carbs", vit="Vit",prot="Prot";


    static String carbsInfo="Carbohydrate, also known as saccharides or carbs, are sugars or starches.\n\n" +
            "They are a major food source and a key form of energy for most organisms.\n\nThey consist of carbon, hydrogen, and oxygen atoms.";

    static String vitsInfo="Vitamin, an organic molecule that is an essential micronutrient which an organism needs in small " +
            "quantities for the proper functioning of its metabolism.\n\nThere are 13 vitamins—vitamins A, C, D, E, K, and the B vitamins (thiamine, riboflavin, niacin, " +
            "pantothenic acid, biotin, B6, B12, and folate).\n\nVitamins have different jobs (in a simple way)—helping you resist infections, keeping your nerves healthy, " +
            "and helping your body get energy from food or your blood to clot properly.";

    static String protsInfo="Proteins, large biomolecules, or macromolecules, consisting of one or more long chains of amino acid residues.\n\n" +
            "The structure and function of our bodies depend on proteins.";
    static String teaInfo = "Tea has caffeine.";

    public static void foodInfo() {
         nutritionTitle=titleStr+foodName;

        if(foodName.trim().equalsIgnoreCase(dengu)){
            desc = "Green grams or mung bean, scientific name: Vigna radiata, a plant species in the legume family. A rich source of protein. 100g contains:\n\n" +
                    "Calories: 347\n\n" +
                    "Total Fat: 1.2g\t1%\n" +
                    "Saturated fat: 0.3g 1%\n" +
                    "Polyunsaturated fat: 0.4g\n" +
                    "Monounsaturated fat: 0.2g\n\n" +
                    "Total Carbohydrate: 63g\t21%\n" +
                    "Dietary fiber: 16g\t64%\n" +
                    "Sugar: 7g\t\n\n" +
                    "Sodium: 15mg\t0%\n" +
                    "Potassium: 1,246mg\t35%\n" +
                    "Protein: 24g\t48%\n" +
                    "Vitamin A: 2%\n" +
                    "Vitamin B6: 20%\n" +
                    "Vitamin C: 8%\n" +
                    "Calcium: 13%\n" +
                    "Iron: 37%\n" +
                    "Magnesium: 47%";

        }

        else if(foodName.trim().equalsIgnoreCase(rice)){
            desc = "Seed of the grass species Oryza glaberrima or Oryza sativa (scientific name). 186g of cooked, enriched, short-grain white rice contains:\n\n" +
                    "Calories: 242\n" +
                    "Fat: 0.4g\n" +
                    "Sodium: 0mg\n" +
                    "Carbohydrates: 53.4g\n" +
                    "Fiber: 0.6g\n" +
                    "Sugars: 0g\n" +
                    "Protein: 4.4g";
        }

        else if(foodName.trim().equalsIgnoreCase(coffee)){
            desc = "Coffee is a brewed drink prepared from roasted coffee beans, the seeds of berries from certain Coffea species. \nWhen coffee " +
                    "berries turn from green to bright red in color – indicating ripeness – they are picked, processed, and dried. " +
                    "Dried coffee seeds are roasted to varying degrees, depending on the desired flavor. Scientific name: Coffea.\n\n" +
                    "Coffee is a known stimulant for the central nervous system and helps improve memory and cognition.\n\n" +
                    "Regular black coffee (without milk or cream) is low in calories. In fact, a typical cup of black coffee only contains around " +
                    "2 calories. However, adding cream or sugar will increase the calorific value.\n" +
                    "Coffee beans also contain polyphenols, a type of antioxidant that are linked to protection against inflammation, which is " +
                    "the root cause of a number of diseases.100 grams contains\n\n" +
                    "Calories 0\n" +
                    "Total Fat 0 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Trans fat regulation 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 2 mg\t0%\n" +
                    "Potassium 49 mg\t1%\n" +
                    "Total Carbohydrate 0 g\t0%\n" +
                    "Dietary fiber 0 g\t0%\n" +
                    "Sugar 0 g\t\n" +
                    "Protein 0.1 g\t0%\n" +
                    "Caffeine 40 mg\t\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t0%\n" +
                    "Calcium\t0%\n" +
                    "Iron\t0%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t0%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t";
        }

        else if(foodName.trim().equalsIgnoreCase(chocolate)){
            desc = "Chocolate is a preparation of roasted and ground cacao seeds that is made in the form of a liquid, paste, or in a block," +
                    " which may also be used as a flavoring ingredient in other foods.\n" +
                    "Cocoa powder is rich in theobromine, which helps to reduce inflammation and can protect you from diseases such as heart disease," +
                    " cancer, and diabetes. Since cocoa is rich in phytonutrients but low in fat and sugar, the calories you get from cocoa " +
                    "powder will be packed with healthy chemicals\n" +
                    "100 grams of chocolate contains:\n\n" +
                    "Calories 546\n" +
                    "Total Fat 31 g\t47%\n" +
                    "Saturated fat 19 g\t95%\n" +
                    "Polyunsaturated fat 1.1 g\t\n" +
                    "Monounsaturated fat 10 g\t\n" +
                    "Trans fat regulation 0.1 g\t\n" +
                    "Cholesterol 8 mg\t2%\n" +
                    "Sodium 24 mg\t1%\n" +
                    "Potassium 559 mg\t15%\n" +
                    "Total Carbohydrate 61 g\t20%\n" +
                    "Dietary fiber 7 g\t28%\n" +
                    "Sugar 48 g\t\n" +
                    "Protein 4.9 g\t9%\n" +
                    "Caffeine 43 mg\t\n" +
                    "Vitamin A\t1%\n" +
                    "Vitamin C\t0%\n" +
                    "Calcium\t5%\n" +
                    "Iron\t44%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t0%\n" +
                    "Cobalamin\t3%\n" +
                    "Magnesium\t36%";
        }

        else if(foodName.trim().equalsIgnoreCase(skuma)){
            desc = "Known as collard greens. Scientific name: Brassica oleracea var. viridis. Cooked with onions and spices." +
                    " Boiled collard greens, drained and without added salt contains:\n\n" +
                    "Calories: 63\n" +
                    "Protein: 5.15g\n" +
                    "Fat: 1.37g\n" +
                    "Carbohydrate: 10.73g\n" +
                    "Fiber: 7.6g\n" +
                    "Sugar: <1g\n" +
                    "Calcium: 268mg\n" +
                    "Iron: 2.15mg\n" +
                    "Magnesium: 40mg\n" +
                    "Phosphorus: 61mg\n" +
                    "Potassium: 222mg\n" +
                    "Sodium: 28mg\n" +
                    "Zinc: 0.44mg\n" +
                    "Vitamin: C 34.6mg\n" +
                    "Folate: 30mcg (micrograms)\n" +
                    "Vitamin A: 722mcg\n" +
                    "Vitamin E: 1.67mg\n" +
                    "Vitamin K: 772.5mcg";
        }

        else if(foodName.trim().equalsIgnoreCase(porridge)){
            desc = "Soft food made by boiling oatmeal or starchy plants or legumes in water or milk until thick. A cup of 100g contains:\n\n" +
                    "Calories: 50" +
                    "Total Fat: 0.2g 0%\n" +
                    "Saturated fat: 0g 0%\n" +
                    "Polyunsaturated fat: 0.1g\n" +
                    "Monounsaturated fat: 0g\n" +
                    "Total Carbohydrate: 11g 3%\n" +
                    "Dietary fiber: 0.5g 2%\n" +
                    "Sugar: 0g\n" +
                    "Cholesterol: 0mg 0%\n" +
                    "Sodium: 6mg 0%\n" +
                    "Potassium: 16mg 0%\n" +
                    "Protein: 1.4g 2%\n" +
                    "Vitamin A: 0%\n" +
                    "Vitamin C: 0%\n" +
                    "Calcium: 8%\n" +
                    "Iron: 20%\n" +
                    "Vitamin D: 0%\n" +
                    "Vitamin B6: 0%\n" +
                    "Cobalamin: 0%\n" +
                    "Magnesium: 1%\n";
        }

        else if(foodName.trim().equalsIgnoreCase(goat)){
            desc = "Scientific name: Capra aegagrus hircus, goat meat is naturally lean, meaning it is much lower in saturated fat and cholesterol. " +
                    "A Serving Size of 85g contains:\n\n" +
                    "Calories: 121.6" +
                    "Total Fat: 2.6g\n" +
                    "Saturated fat: 0.8g\n" +
                    "Monounsaturated fat: 1.2g\n" +
                    "Polyunsaturated fat: 0.2g\n\n" +
                    "Cholesterol: 63.8mg\n" +
                    "Sodium: 73.1mg\t4%\n" +
                    "Potassium: 344.3mg\n" +
                    "Protein: 23g\n" +
                    "Vitamins and minerals";
        }

        else if(foodName.trim().equalsIgnoreCase(ugali)){
            desc = "Food of maize flour (some include other cereals like sorghum) boiled into a doughy cake. Contains:\n\n" +
                    "Carbohydrate: 38g 84%\n" +
                    "Fats: 2g 10%\n" +
                    "Protein: 3g 7%";
        }

        else if(foodName.trim().equalsIgnoreCase(chapati)){
            desc = "Flat pancake-like bread cooked on a griddle. A small chapati contains:\n\n" +
                    "Calories: 70\n" +
                    "Protein: 3g\n" +
                    "Fat: 0.4g\n" +
                    "Carbohydrates: 15g";
        }

        else if(foodName.trim().equalsIgnoreCase(beans)){
            desc = "Seed of one of several genera of the flowering plant family Fabaceae. Scientific name: Phaseolus vulgaris. 171g of boiled pinto beans contains:\n\n" +
                    "Protein: 15g\n" +
                    "Fat: 1g\n" +
                    "Carbohydrates: 45g\n" +
                    "Fiber: 15g\n" +
                    "Iron: 20% of the Daily Value (DV)\n" +
                    "Calcium: 8% of the DV\n" +
                    "Magnesium: 21% of the DV\n" +
                    "Phosphorous: 25% of the DV\n" +
                    "Potassium: 21% of the DV\n" +
                    "Folate: 74% of the DV";
        }

        else if(foodName.trim().equalsIgnoreCase(snacks)){
            desc = "Are a portion of food often smaller than a regular meal, generally eaten between meals." +
                    "Snacks come in a variety of forms including packaged and processed foods and items made from fresh ingredients at home." +
                    "Varied categories give varied nutritional facts...(more info later)";
        }

        else if(foodName.trim().equalsIgnoreCase(bread)){
            desc = "Staple food prepared from a dough of flour and water, usually by baking. 100g of bread contains:\n\n" +
                    "Calories: 265\n" +
                    "Total Fat: 3.2g 4% \n" +
                    "Sodium: 491mg 20%\n" +
                    "Potassium: 115mg 3%\n" +
                    "Total Carbohydrate: 49g 16%\n" +
                    "Protein: 9g 18%\n" +
                    "Calcium: 26%\n" +
                    "Iron: 19%\n" +
                    "Vitamin B6: 5%\n" +
                    "Magnesium: 6%";
        }

        else if(foodName.trim().equalsIgnoreCase(tea)){
            desc = "Tea is a beverage commonly prepared by pouring hot or boiling water over cured or " +
                    "fresh leaves of tea plant, scientific name: Camellia sinensis, an evergreen shrub.\n\n After water, " +
                    "it is the most widely consumed drink in the world.Amount per 100 grams (Some people add sugar or milk to enhance taste)\n\n"+
                    "Calories: 1\n" +
                    "Sodium: 4mg\n" +
                    "Potassium: 18mg\n" +
                    "Total Carbohydrate: 0.2g\n" +
                    "Sugar: 0g\n" +
                    "Protein: 0.1g\n" +
                    "Caffeine: 11mg";
        }

        else if(foodName.trim().equalsIgnoreCase(fish)){
            desc = "Gill-bearing aquatic craniate animals that lack limbs with digits, mostly cold-blooded. Amount Per 100 grams contains:\n\n" +
                    "Calories: 206\n" +
                    "Total Fat: 12g 18%\n" +
                    "Saturated fat: 2.5g 12%\n" +
                    "Polyunsaturated fat: 4.4g\n" +
                    "Monounsaturated fat: 4.4g\n" +
                    "Cholesterol: 63mg 21%\n" +
                    "Sodium: 61mg 2%\n" +
                    "Potassium: 384mg 10%\n" +
                    "Protein: 22g 44%\n" +
                    "Vitamin A: 1%\n" +
                    "Vitamin C: 6%\n" +
                    "Calcium: 1%\n" +
                    "Iron: 1%\n" +
                    "Vitamin B-6: 30%\n" +
                    "Cobalamin: 46%\n" +
                    "Magnesium:7%";
        }

        else if(foodName.trim().equalsIgnoreCase(githeri)){
            desc = "Also called muthere or mutheri, is a meal of maize and legumes, mostly beans of any type mixed and boiled together. " +
                    "A cup of 199g contains:\n\n" +
                    "Calories: 185\n" +
                    "Total Fat: 4.5g 7%\n" +
                    "Saturated Fat: 0.5g 2%\n" +
                    "Trans Fat: 0g\n" +
                    "Cholesterol: 0mg 0%\n" +
                    "Sodium: 5.4mg 0%\n" +
                    "Potassium: 463mg 13%\n" +
                    "Total Carbohydrates: 30g 10%\n" +
                    "Dietary Fiber: 8.8g 35%\n" +
                    "Sugars: 2.9g\n" +
                    "Protein: 9.1g\n" +
                    "Vitamin A: 12%\n" +
                    "Vitamin C: 10%\n" +
                    "Calcium: 3%\n" +
                    "Iron: 12%";

        }

        else if(foodName.trim().equalsIgnoreCase(cabbage)){
            desc = "A leafy green, red, or white biennial plant, scientific name: Brassica oleracea var. capitata, closely related to broccoli and cauliflower, " +
                    "Brussels sprouts and Savoy cabbage.\n100g of cabbage contains:\n\n" +
                    "Calories: 25\n\n" +
                    "Total Fat: 0.1g 0%\n" +
                    "Saturated fat: 0g 0%\n" +
                    "Polyunsaturated fat: 0g \n" +
                    "Monounsaturated fat: 0g \n\n" +
                    "Total Carbohydrate: 6g 2%\n" +
                    "Dietary fiber: 2.5g 10%\n" +
                    "Sugar: 3.2g \n\n" +
                    "Cholesterol: 0mg 0%\n" +
                    "Sodium: 18mg 0%\n" +
                    "Potassium: 170mg 4%\n" +
                    "Protein: 1.3g 2%\n" +
                    "Vitamin A: 1%\n" +
                    "Vitamin C: 60%\n" +
                    "Calcium: 4%\n" +
                    "Iron: 2%\n" +
                    "Vitamin B6: 5%\n" +
                    "Magnesium: 3%";

        }
        else if(foodName.trim().equalsIgnoreCase(eggs)){
            desc = "Eggs are laid by female animals of many different species, including birds, reptiles, amphibians, a few mammals, and fish." +
                    " This application focus on hen egg. 100g of egg contains:\n\n" +
                    "Calories: 155\n\n" +
                    "Total Fat: 11g 16%\n" +
                    "Saturated fat: 3.3g 16%\n" +
                    "Polyunsaturated fat: 1.4g \n" +
                    "Monounsaturated fat: 4.1g\n\n" +
                    "Total Carbohydrate: 1.1g 0%\n" +
                    "Dietary fiber: 0 g 0%\n" +
                    "Sugar: 1.1g \n\n" +
                    "Cholesterol: 373mg 124%\n" +
                    "Sodium: 124mg 5%\n" +
                    "Potassium: 126mg 3%\n" +
                    "Protein: 13g 26%\n" +
                    "Vitamin A: 10%\n" +
                    "Vitamin C: 0%\n" +
                    "Calcium: 5%\n" +
                    "Iron: 6%\n" +
                    "Vitamin D: 21%\n" +
                    "Vitamin B6: 5%\n" +
                    "Cobalamin: 18%\n" +
                    "Magnesium: 2%";

        }
        else if(foodName.trim().equalsIgnoreCase(waru)){
            desc = "Underground tubers that grow on the roots of the potato plant, scientific name: Solanum tuberosum L. " +
                    "From the nightshade family and related to tomatoes and tobacco. 100g of boiled potatoes (cooked with the skin but without salt) contains:\n\n" +
                    "Calories: 87\n" +
                    "Water: 77%\n" +
                    "Protein: 1.9g\n" +
                    "Carbohydrates: 20.1g\n" +
                    "Sugar: 0.9g\n" +
                    "Fiber: 1.8g\n" +
                    "Fat: 0.1g";

        }
        else if(foodName.trim().equalsIgnoreCase(bananas)){
            desc = "A banana is an elongated, edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa" +
                    "Scientific names of most cultivated bananas are Musa acuminata, Musa balbisiana, and Musa  paradisiaca. " +
                    "100g of bananas contains:\n\n" +
                    "Calories: 89\n" +
                    "Total Fat: 0.3g 0%\n" +
                    "Saturated fat: 0.1g 0%\n" +
                    "Polyunsaturated fat: 0.1g\n" +
                    "Monounsaturated fat: 0g\n" +
                    "Total Carbohydrate: 23g 7%\n" +
                    "Dietary fiber: 2.6g 10%\n" +
                    "Sugar: 12g \n" +
                    "Cholesterol: 0mg 0%\n" +
                    "Sodium: 1mg 0%\n" +
                    "Potassium: 358mg 10%\n" +
                    "Protein: 1.1g 2%\n" +
                    "Vitamin A: 1%\n" +
                    "Vitamin C: 14%\n" +
                    "Calcium: 0%\n" +
                    "Iron: 1%\n" +
                    "Vitamin D: 0%\n" +
                    "Vitamin B6: 20%\n" +
                    "Cobalamin: 0%\n" +
                    "Magnesium: 6%";

        }
        else if(foodName.trim().equalsIgnoreCase(beef)){
            desc = "Culinary name for meat from cattle, Bos is the genus of wild and domestic cattle. 100g of beef contains:\n\n" +
                    "Calories: 250\n" +
                    "Total Fat: 15g 23%\n" +
                    "Saturated fat: 6g 30%\n" +
                    "Polyunsaturated fat: 0.5g\n" +
                    "Monounsaturated fat: 7g\n" +
                    "Total Carbohydrate: 0g 0%\n" +
                    "Dietary fiber: 0g 0%\n" +
                    "Sugar: 0g \n" +
                    "Trans fat regulation: 1.1g\n" +
                    "Cholesterol: 90mg 30%\n" +
                    "Sodium: 72mg 3%\n" +
                    "Potassium: 318mg 9%\n" +
                    "Protein: 26g 52%\n" +
                    "Vitamin A: 0%\n" +
                    "Vitamin C: 0%\n" +
                    "Calcium: 1%\n" +
                    "Iron: 14%\n" +
                    "Vitamin D: 1%\n" +
                    "Vitamin B6: 20%\n" +
                    "Cobalamin: 43%\n" +
                    "Magnesium: 5%";

        }
        else if(foodName.trim().equalsIgnoreCase(chicken)){
            desc = "Chicken, scientific name: Gallus gallus domesticus, is a type of domesticated fowl, a subspecies of the red jungle fowl (Gallus gallus)." +
                    " Chickens are one of the most common and widespread domestic animals. 100g of chicken contains:\n\n" +
                    "Calories: 239\n" +
                    "Total Fat: 14g 21%\n" +
                    "Saturated fat: 3.8g 19%\n" +
                    "Polyunsaturated fat: 3g\n" +
                    "Monounsaturated fat: 5g\n" +
                    "Total Carbohydrate: 0g 0%\n" +
                    "Dietary fiber: 0g 0%\n" +
                    "Sugar: 0g \n" +
                    "Cholesterol: 88mg 29%\n" +
                    "Sodium: 82mg 3%\n" +
                    "Potassium: 223mg 6%\n" +
                    "Protein: 27g 54%\n" +
                    "Vitamin A: 3%\n" +
                    "Vitamin C: 0%\n" +
                    "Calcium: 1%\n" +
                    "Iron: 7%\n" +
                    "Vitamin D: 0%\n" +
                    "Vitamin B6: 20%\n" +
                    "Cobalamin: 5%\n" +
                    "Magnesium: 5%";

        }
        else if(foodName.trim().equalsIgnoreCase(rabuon)){
            desc = "Dicotyledonous starchy plant that belongs to the bindweed or morning glory family Convolvulaceae, " +
                    "scientific name: Ipomoea batatas. 100g contains:\n\n" +
                    "Calories: 86\n" +
                    "Total Fat: 0.1g 0%\n" +
                    "Saturated fat: 0g 0%\n" +
                    "Polyunsaturated fat: 0g\n" +
                    "Monounsaturated fat: 0g\n" +
                    "Total Carbohydrate: 20g 6%\n" +
                    "Dietary fiber: 3g 12%\n" +
                    "Sugar: 4.2g \n" +
                    "Cholesterol: 0mg 0%\n" +
                    "Sodium: 55mg 2%\n" +
                    "Potassium: 337mg 9%\n" +
                    "Protein: 1.6g 3%\n" +
                    "Vitamin A: 283%\n" +
                    "Vitamin C: 4%\n" +
                    "Vitamin D: 0%\n" +
                    "Vitamin B6: 10%\n" +
                    "Calcium: 3%\n" +
                    "Iron: 3%\n" +
                    "Cobalamin: 0%\n" +
                    "Magnesium: 6%";

        }
        else if(foodName.trim().equalsIgnoreCase(ackee)){
            desc ="Ackee is a plant that produces fruit. Ripe ackee fruit is eaten as food and is considered a dietary staple in Jamaica.\n" +
                    "However, unripe or overripe ackee fruit is very poisonous. Same as it's seeds.\n" +
                    "100g serving of Ackee, canned and drained contains:\n\n" +
                    "Energy (625 kJ or 151 kcal)\n" +
                    "Water (76.7 g),\n" +
                    "Protein (2.9 g)\n" +
                    "Fat (15.2 g)\n" +
                    "Saturated fat (0 g)\n" +
                    "Cholesterol (0 mg)\n" +
                    "Total carbohydrate (0.8 g)\n" +
                    "Dietary fibre (2.7 g)\n" +
                    "Calcium (35 mg)\n" +
                    "Iron (0.7 mg)\n" +
                    "Potassium (270 mg)\n" +
                    "Sodium (240 mg)\n" +
                    "Zinc (1 mg)\n" +
                    "vit A - thiamin (0.03 mg)\n" +
                    "Riboflavin (0.07 mg)\n" +
                    "Niacin (1.1 mg)\n" +
                    "Total folacin (41 microgram)\n" +
                    "Vitamin C (30 mg)."
            ;}
        else if(foodName.trim().equalsIgnoreCase(aliya)){
            desc ="Dried meat is a feature of many cuisines around the world. Luo's call it aliya and Americans call it 'jerky'. " +
                    "It is salted before drying to ensure it stays fresh.\nIt can be made from almost any lean meat, " +
                    "including beef, goat, venison(deer meat) or smoked turkey breast. Raw poultry is not recommended for jerky because of the " +
                    "texture and flavor of the end product. 100 grams jerky contains: \n\n" +
                    "Calories 271\n" +
                    "Total Fat 3.7g 5%\n" +
                    "Saturated fat 1.6g 8%\n" +
                    "Polyunsaturated fat 0.4g\n" +
                    "Monounsaturated fat 1.2g\n" +
                    "Cholesterol 164mg 54%\n" +
                    "Sodium 950mg 39%\n" +
                    "Potassium 810mg 23%\n" +
                    "Total Carbohydrate 0g 0%\n" +
                    "Dietary fiber 0g 0%\n" +
                    "Sugar 0g \n" +
                    "Protein 59g 117%\n" +
                    "Vitamin A 0%\n" +
                    "Vitamin C 0%\n" +
                    "Calcium 1%\n" +
                    "Iron 61%\n" +
                    "Vitamin D 0%\n" +
                    "Vitamin B-6 25%\n" +
                    "Cobalamin 251%\n" +
                    "Magnesium 15%\n";
        }
        else if(foodName.trim().equalsIgnoreCase(apple)){
            desc ="Edible fruit produced by an apple tree. Accepted scientific name is Malus pumila, but its also referred to as Malus domestica." +
                    "\nThis nutrition information is provided by the USDA(United State Agriculture Department) for one medium-sized (182g) apple (aprx 3” in diameter)\n\n" +
                    "Calories: 95\n" +
                    "Fat: 0.3g\n" +
                    "Sodium: 1.8mg\n" +
                    "Carbohydrates: 25g\n" +
                    "Fiber: 4.4g\n" +
                    "Sugars: 18.9g\n" +
                    "Protein: 0.5g";
        }
        else if(foodName.trim().equalsIgnoreCase(cucumber)){
            desc ="Cucumber is a widely-cultivated creeping vine plant, it's in the Cucurbitaceae gourd family that bears cucumiform fruits," +
                    " which are used as vegetables. Scientific name Cucumis sativus. One 11-ounce (300-gram) unpeeled, raw cucumber contains:\n\n" +
                    "Calories: 45\n" +
                    "Total fat: 0 grams\n" +
                    "Carbs: 11 grams\n" +
                    "Protein: 2 grams\n" +
                    "Fiber: 2 grams\n" +
                    "Vitamin C: 14% of the RDI\n" +
                    "Vitamin K: 62% of the RDI\n" +
                    "Magnesium: 10% of the RDI\n" +
                    "Potassium: 13% of the RDI\n" +
                    "Manganese: 12% of the RDI";
        }
        else if(foodName.trim().equalsIgnoreCase(nduma)){
            desc ="Arrowroot is a starch obtained from the rhizomes of several tropical plants. Scientific name Maranta arundinacea. 100 grams contains:\n\n" +
                    "Calories 65\n" +
                    "Total Fat 0.2g 0%\n" +
                    "Saturated fat 0g 0%\n" +
                    "Polyunsaturated fat 0.1g \n" +
                    "Monounsaturated fat 0g \n" +
                    "Cholesterol 0mg 0%\n" +
                    "Sodium 26mg 1%\n" +
                    "Potassium 454mg 12%\n" +
                    "Total Carbohydrate 13g 4%\n" +
                    "Dietary fiber 1.3g 5%\n" +
                    "Protein 4.2g 8%\n" +
                    "Vitamin A 0%\n" +
                    "Vitamin C 3%\n" +
                    "Calcium 0%\n" +
                    "Iron 12%\n" +
                    "Vitamin D 0%\n" +
                    "Vitamin B-6 15%\n" +
                    "Cobalamin 0%\n" +
                    "Magnesium 6%";
        }
        else if(foodName.trim().equalsIgnoreCase(avocado)){
            desc ="Avocado, a tree likely originating from south central Mexico, is classified as a " +
                    "member of the flowering plant family Lauraceae.\n Scientific name Persea americana. 100 grams contains:\n\n" +
                    "Calories 160\n" +
                    "Total Fat 15 g\t23%\n" +
                    "Saturated fat 2.1 g\t10%\n" +
                    "Polyunsaturated fat 1.8 g\t\n" +
                    "Monounsaturated fat 10 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 7 mg\t0%\n" +
                    "Potassium 485 mg\t13%\n" +
                    "Total Carbohydrate 9 g\t3%\n" +
                    "Dietary fiber 7 g\t28%\n" +
                    "Sugar 0.7 g\t\n" +
                    "Protein 2 g\t4%\n" +
                    "Vitamin A\t2%\n" +
                    "Vitamin C\t16%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t3%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t15%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t7%";
        }
        else if(foodName.trim().equalsIgnoreCase(matoke)){
            desc ="Some types of unripe bananas can be cooked, known as matoke. A banana is an elongated, edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa\n" +
        " Scientific names of most cultivated bananas are Musa acuminata, Musa balbisiana, and Musa  paradisiaca. 100g of bananas contains:\n\n" +
                    "Calories 122\n" +
                    "Total Fat 0.4 g\t0%\n" +
                    "Saturated fat 0.1 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 4 mg\t0%\n" +
                    "Potassium 499 mg\t14%\n" +
                    "Total Carbohydrate 32 g\t10%\n" +
                    "Dietary fiber 2.3 g\t9%\n" +
                    "Sugar 15 g\t\n" +
                    "Protein 1.3 g\t2%\n" +
                    "Vitamin A\t22%\n" +
                    "Vitamin C\t30%\n" +
                    "Calcium\t0%\n" +
                    "Iron\t3%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t15%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t9%";
        }
        else if(foodName.trim().equalsIgnoreCase(ripebanana)){
            desc ="A banana is an elongated, edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa.\n"+
                    "  Scientific names of most cultivated bananas are Musa acuminata, Musa balbisiana, and Musa  paradisiaca. one medium ripe banana, provides about:\n\n" +
                    "Calories 110\n" +
                    "Fat 0g\n" +
                    "Protein 1g\n" +
                    "Carbohydrate 28g\n" +
                    "Sugar (naturally occurring) 15g\n" +
                    "Fiber 3g\n" +
                    "Potassium 450mg";
        } //bananaripe, bhajia not complete
        else if(foodName.trim().equalsIgnoreCase(bhajia)){
            desc ="A bhaji, bhajiya or bajji is a spicy snack or entree dish similar to a fritter," +
                    " originating from the Indian subcontinent, with several variants";
        }
        else if(foodName.trim().equalsIgnoreCase(biryani)){
            desc ="Biryani is a mixed rice dish with its origins among the Muslims of the Indian subcontinent. It is made with spices, rice, and meat, " +
                    "and sometimes, in addition, eggs and/or potatoes in certain regional varieties. " +
                    "241 calories that come from one serving of Vegetable Biryani\n\n" +
                    "Value per per serving\t% Daily Values\n" +
                    "Protein\t4.8 g\t9%\n" +
                    "Carbohydrates\t13.9 g\t5%\n" +
                    "Fiber\t3.3 g\t13%\n" +
                    "Fat\t17.9 g\t27%";
        }
        else if(foodName.trim().equalsIgnoreCase(brocolli)){
            desc ="Broccoli is an edible green plant in the cabbage family whose large flowering head, stalk and small associated leaves are eaten " +
                    "as a vegetable. Scientific name: Brassica oleracea var. italica. 100 grams contains\n\n" +
                    "Calories 34\n" +
                    "Total Fat 0.4 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 33 mg\t1%\n" +
                    "Potassium 316 mg\t9%\n" +
                    "Total Carbohydrate 7 g\t2%\n" +
                    "Dietary fiber 2.6 g\t10%\n" +
                    "Sugar 1.7 g\t\n" +
                    "Protein 2.8 g\t5%\n" +
                    "Vitamin A\t12%\n" +
                    "Vitamin C\t148%\n" +
                    "Calcium\t4%\n" +
                    "Iron\t3%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t10%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t5%";
        }
        else if(foodName.trim().equalsIgnoreCase(burger)){
            desc ="A hamburger is a sandwich consisting of one or more cooked patties of ground meat, usually beef, " +
                    "placed inside a sliced bread roll or bun. The patty may be pan fried, grilled, smoked or flame broiled. 100 grams contains\n\n" +
                    "Calories 295\n" +
                    "Total Fat 14 g\t21%\n" +
                    "Saturated fat 5 g\t25%\n" +
                    "Polyunsaturated fat 0.4 g\t\n" +
                    "Monounsaturated fat 5 g\t\n" +
                    "Trans fat regulation 0.8 g\t\n" +
                    "Cholesterol 47 mg\t15%\n" +
                    "Sodium 414 mg\t17%\n" +
                    "Potassium 226 mg\t6%\n" +
                    "Total Carbohydrate 24 g\t8%\n" +
                    "Dietary fiber 0.9 g\t3%\n" +
                    "Sugar 4.2 g\t\n" +
                    "Protein 17 g\t34%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t0%\n" +
                    "Calcium\t10%\n" +
                    "Iron\t16%\n" +
                    "Vitamin B-6\t10%\n" +
                    "Cobalamin\t23%\n" +
                    "Magnesium\t5%\t";
        }
        else if(foodName.trim().equalsIgnoreCase(fermentedMilk)){
            desc ="Fermented milk is the collective name for several milk products such as yoghurt, ymer, kefir, " +
                    "cultured buttermilk, filmjölk (Scandinavian sour milk), cultured cream and koumiss (a product based on mares' milk). " +
                    "In 2017, a study from The Nutrition Society found the health benefits of fermented milk drinks such as kefir included improved " +
                    "digestion, anti-inflammatory effects and the stimulation of antioxidants, which can aid disease prevention. 1 cup contains:\n\n" +
                    "Calories 110\n" +
                    "Calories from Fat 22.5 (20.5%)\n" +
                    "Total Fat 2.5g\t-\n" +
                    "Saturated fat 1.5g\t-\n" +
                    "Trans fat 0.1g\t-\n" +
                    "Cholesterol 15mg\t-\n" +
                    "Sodium 115mg\t5%\n" +
                    "Carbohydrates 8g\t-\n" +
                    "Net carbs 8g\t-\n" +
                    "Sugar 8g\t-\n" +
                    "Fiber 0g\t0%\n" +
                    "Protein 9g\t\n" +
                    "Vitamins and minerals\n" +
                    "Vitamin A 225μg\t25%\n" +
                    "Vitamin C 0mg\t0%\n" +
                    "Calcium 300mg\t30%\n" +
                    "Iron 0mg\t0%\n" +
                    "Fatty acids\n" +
                    "Amino acids";
        }
        else if(foodName.trim().equalsIgnoreCase(carrot)){
            desc ="Carrot is a root vegetable, usually orange in color, though purple, black, red, white, and yellow cultivars exist.\n" +
                    "Scientific name: Daucus carota subsp. sativus. 100 grams contains:\n\n" +
                    "Calories 41\n" +
                    "Total Fat 0.2 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Trans fat regulation 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 69 mg\t2%\n" +
                    "Potassium 320 mg\t9%\n" +
                    "Total Carbohydrate 10 g\t3%\n" +
                    "Dietary fiber 2.8 g\t11%\n" +
                    "Sugar 4.7 g\t\n" +
                    "Protein 0.9 g\t1%\n" +
                    "Vitamin A\t334%\n" +
                    "Vitamin C\t9%\n" +
                    "Calcium\t3%\n" +
                    "Iron\t1%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t3%";
        }
        else if(foodName.trim().equalsIgnoreCase(cassava)){
            desc = "Cassava is a root vegetable. It is the underground part of the cassava shrub, which has the Scientific name(Latin name): 'Manihot esculenta'. " +
                    "Like potatoes and yams, it is a tuber crop. Cassava roots have a similar shape to sweet potatoes." +
                    "People can also eat the leaves of the cassava plant. Cassava contains cyanogenic glycosides, which can result in fatal\n\n" +
                    "Cyanide(A chemical compound that contains the group C≡N, cyano group, which consists of a carbon atom triple-bonded to a nitrogen atom) " +
                    "is poisonous if not properly detoxified by soaking, drying, and scraping before being consumed. Liquid forms of cyanide can be absorbed through the skin. 100 grams contains\n\n" +
                    "Calories 159\n" +
                    "Total Fat 0.3 g\t0%\n" +
                    "Saturated fat 0.1 g\t0%\n" +
                    "Polyunsaturated fat 0 g\t\n" +
                    "Monounsaturated fat 0.1 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 14 mg\t0%\n" +
                    "Potassium 271 mg\t7%\n" +
                    "Total Carbohydrate 38 g\t12%\n" +
                    "Dietary fiber 1.8 g\t7%\n" +
                    "Sugar 1.7 g\t\n" +
                    "Protein 1.4 g\t2%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t34%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t1%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t5%";
        }
        else if(foodName.trim().equalsIgnoreCase(cherimoya)){
            desc ="Also chirimoya, a green, cone-shaped fruit with scaly skin and creamy, sweet flesh. An edible fruit-bearing species of the genus Annona from " +
                    "the family Annonaceae. Scientific name: Annona cherimola. 100 grams contains:\n\n" +
                    "Calories 75\n" +
                    "Total Fat 0.7 g\t1%\n" +
                    "Saturated fat 0.2 g\t1%\n" +
                    "Polyunsaturated fat 0.2 g\t\n" +
                    "Monounsaturated fat 0.1 g\t\n" +
                    "Trans fat regulation 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 7 mg\t0%\n" +
                    "Potassium 287 mg\t8%\n" +
                    "Total Carbohydrate 18 g\t6%\n" +
                    "Dietary fiber 3 g\t12%\n" +
                    "Sugar 13 g\t\n" +
                    "Protein 1.6 g\t3%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t21%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t1%\n" +
                    "Vitamin B-6\t15%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t4%\t";
        }
        else if(foodName.trim().equalsIgnoreCase(chips)){
            desc ="Potato chips, or crisps, are thin slices of potato that have been either deep fried or baked until crunchy. " +
                    "They are commonly served as a snack, side dish, or appetizer. 100 grams contains:\n\n" +
                    "Calories 536\n" +
                    "Total Fat 35 g\t53%\n" +
                    "Saturated fat 11 g\t55%\n" +
                    "Polyunsaturated fat 12 g\t\n" +
                    "Monounsaturated fat 10 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 8 mg\t0%\n" +
                    "Potassium 1,275 mg\t36%\n" +
                    "Total Carbohydrate 53 g\t17%\n" +
                    "Dietary fiber 4.8 g\t19%\n" +
                    "Sugar 0.2 g\t\n" +
                    "Protein 7 g\t14%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t51%\n" +
                    "Calcium\t2%\n" +
                    "Iron\t8%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t35%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t16%";
        }//next
        else if(foodName.trim().equalsIgnoreCase(coconutMilk)){
            desc ="Coconut milk is an opaque, milky-white liquid extracted from the grated pulp of mature coconuts. " +
                    "The opacity and rich taste of coconut milk is due to its high oil content, most of which is saturated fat. " +
                    "Scientific name of coconut : cocus nucifera. 100 grams of coconut milk contains:\n\n" +
                    "Calories 230\n" +
                    "Total Fat 24 g\t36%\n" +
                    "Saturated fat 21 g\t104%\n" +
                    "Polyunsaturated fat 0.3 g\t\n" +
                    "Monounsaturated fat 1 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 15 mg\t0%\n" +
                    "Potassium 263 mg\t7%\n" +
                    "Total Carbohydrate 6 g\t2%\n" +
                    "Dietary fiber 2.2 g\t8%\n" +
                    "Sugar 3.3 g\t\n" +
                    "Protein 2.3 g\t4%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t4%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t8%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t0%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t9%";}
        else if(foodName.trim().equalsIgnoreCase(salad)){
            desc ="Kachumbari is a salad dish that is popular in the cuisines of the African Great Lakes region." +
                    " It is an uncooked salad dish consisting of chopped tomatoes, onions, and chili peppers. Nutritional value varies and mostly consist of \n\n" +
                    "Calories: 24.6.\n" +
                    "Calories from Fat 1 g 7 %\n" +
                    "Total Fat 0.2 g 0 %\n" +
                    "Saturated Fat 0 g 0 %\n" +
                    "Cholesterol 0 mg 0 %\n" +
                    "Sodium 4.8 mg 0 %\n" +
                    "Total Carbohydrate 5.6 g 1 %\n" +
                    "Dietary Fiber 1.3 g 5 % Sugars 3.2 g 12 %\n" +
                    "Protein 0.9 g 1 %";}
        else if(foodName.trim().equalsIgnoreCase(cupuacu)){
            desc ="Cupuaçu is an arboreal fruit species. It's a chocolaty, nutrient-dense fruit native to Brazil. " +
                    "It's widely used in skin and hair products due to its high fat content, which can help moisturize your skin and hair. " +
                    "The taste of cupuaçu is often compared to chocolate, with hints of banana, melon, and pineapple. " +
                    "Scientific name: Theobroma grandiflorum. Nutrition facts for a serving size of 100 grams\n\n" +
                    "Calories 55.8\tCalories from Fat 5 (9%)\n" +
                    "Total Fat 0.6g\t-\n" +
                    "Sodium 0mg\t0%\n" +
                    "Carbohydrates 11.6g\t-\n" +
                    "Net carbs 11.2g\t-\n" +
                    "Fiber 0.4g\t2%\n" +
                    "Protein 1.3g\t\n" +
                    "Vitamins and minerals\n" +
                    "Vitamin A 0μg\t0%\n" +
                    "Vitamin C 0mg\t0%\n" +
                    "Calcium 0mg\t0%\n" +
                    "Iron 0mg\t0%";}
        else if(foodName.trim().equalsIgnoreCase(dates)){
            desc ="Dates are the fruit of the date palm tree, which is grown in many tropical regions of the world. Dates have become quite popular in recent years. " +
                    "A 3.5-ounce (100-gram) serving provides the following nutrients:\n\n" +
                    "Calories: 277\n" +
                    "Carbs: 75 grams\n" +
                    "Fiber: 7 grams\n" +
                    "Protein: 2 grams\n" +
                    "Potassium: 20% of the RDI\n" +
                    "Magnesium: 14% of the RDI\n" +
                    "Copper: 18% of the RDI\n" +
                    "Manganese: 15% of the RDI\n" +
                    "Iron: 5% of the RDI\n" +
                    "Vitamin B6: 12% of the RDI\n" +
                    "Dates are also high in antioxidants, which may contribute to many of their health benefits";}
        else if(foodName.trim().equalsIgnoreCase(dragonFruit)){
            desc ="Dragon fruit grows on the Hylocereus cactus, also known as the Honolulu queen, whose flowers only open at night.  " +
                    "It is a tropical fruit with a unique appearance, crunchy texture and sweet taste. \n\nPitaya usually refers to fruit of the genus Stenocereus, " +
                    "while pitahaya or dragon fruit refers to fruit of the genus Hylocereus. Nutrition facts for a serving of 3.5 ounces(100 grams):\n\n" +
                    "Calories: 60\n" +
                    "Protein: 1.2 grams\n" +
                    "Fat: 0 grams\n" +
                    "Carbs: 13 grams\n" +
                    "Fiber: 3 grams\n" +
                    "Vitamin C: 3% of the RDI\n" +
                    "Iron: 4% of the RDI\n" +
                    "Magnesium: 10% of the RDI";
        }
        else if(foodName.trim().equalsIgnoreCase(durian)){
            desc ="A tropical fruit distinguished by its large size and spiky, hard outer shell."+
                    "It has a smelly, custard-like flesh with large seeds. " +
                    "One cup (243 grams) of pulp provides:\n\n" +
                    "Calories: 357\n" +
                    "Fat: 13 grams\n" +
                    "Carbs: 66 grams\n" +
                    "Fiber: 9 grams\n" +
                    "Protein: 4 grams\n" +
                    "Vitamin C: 80% of the Daily Value (DV)\n" +
                    "Thiamine: 61% of the DV\n" +
                    "Manganese: 39% of the DV\n" +
                    "Vitamin B6: 38% of the DV\n" +
                    "Potassium: 30% of the DV\n" +
                    "Riboflavin: 29% of the DV\n" +
                    "Copper: 25% of the DV\n" +
                    "Folate: 22% of the DV\n" +
                    "Magnesium: 18% of the DV\n" +
                    "Niacin: 13% of the DV";
        }
        else if(foodName.trim().equalsIgnoreCase(eggplant)){
            desc ="Eggplants, also known as aubergines, belong to the nightshade family of plants and are used in many different dishes around the world. " +
                    "\nAlthough often considered a vegetable, they’re technically a fruit, as they grow from a flowering plant and contain seeds.. Scientific name: Solanum melongena. " +
                    "100 grams contains\n\n" +
                    "Calories 25\n" +
                    "Total Fat 0.2 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 2 mg\t0%\n" +
                    "Potassium 229 mg\t6%\n" +
                    "Total Carbohydrate 6 g\t2%\n" +
                    "Dietary fiber 3 g\t12%\n" +
                    "Sugar 3.5 g\t\n" +
                    "Protein 1 g\t2%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t3%\n" +
                    "Calcium\t0%\n" +
                    "Iron\t1%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t3%";
        }
        else if(foodName.trim().equalsIgnoreCase(figs)){
            desc ="Figs are the fruit of the ficus tree, which is part of the mulberry family (Moraceae). " +
                    "Figs have a unique, sweet taste, soft and chewy texture" +
                    "One small (40-gram) fresh fig contains (1Trusted Source):\n" +
                    "\n" +
                    "Calories: 30\n" +
                    "Protein: 0 grams\n" +
                    "Fat: 0 grams\n" +
                    "Carbs: 8 grams\n" +
                    "Fiber: 1 gram\n" +
                    "Copper: 3% of the Daily Value (DV)\n" +
                    "Magnesium: 2% of the DV\n" +
                    "Potassium: 2% of the DV\n" +
                    "Riboflavin: 2% of the DV\n" +
                    "Thiamine: 2% of the DV\n" +
                    "Vitamin B6: 3% of the DV\n" +
                    "Vitamin K: 2% of the DV";}
        else if(foodName.trim().equalsIgnoreCase(gizzard)){
            desc ="Also referred to as the ventriculus, gastric mill, and gigerium, is an organ found in the digestive tract of some animals. This app focuses on" +
                    "chicken gizzard." +
                    "Amount Per \n" +
                    "100 grams\n" +
                    "100 grams\n" +
                    "Calories 94\n" +
                    "% Daily Value*\n" +
                    "Total Fat 2.1 g\t3%\n" +
                    "Saturated fat 0.5 g\t2%\n" +
                    "Polyunsaturated fat 0.4 g\t\n" +
                    "Monounsaturated fat 0.5 g\t\n" +
                    "Trans fat regulation 0.1 g\t\n" +
                    "Cholesterol 240 mg\t80%\n" +
                    "Sodium 69 mg\t2%\n" +
                    "Potassium 237 mg\t6%\n" +
                    "Total Carbohydrate 0 g\t0%\n" +
                    "Dietary fiber 0 g\t0%\n" +
                    "Sugar 0 g\t\n" +
                    "Protein 18 g\t36%\n" +
                    "Vitamin A\t1%\tVitamin C\t6%\n" +
                    "Calcium\t1%\tIron\t13%\n" +
                    "Vitamin B-6\t5%\tCobalamin\t20%\n" +
                    "Magnesium\t3%\t";
        }
        else if(foodName.trim().equalsIgnoreCase(grilledMeat)){
            desc ="Grilling is a form of cooking that involves dry heat applied to the surface of food, commonly from above, below or from " +
                    "the side. Grilling usually involves a significant amount of direct, radiant heat, and tends to be used for cooking meat.\n\n " +
                    "Serving Size: steak (221g)\n\n" +
                    "Calories from Fat 367\n" +
                    "Calories 614\n" +
                    "Total Fat 41g\n" +
                    "Saturated Fat 16g\n" +
                    "Polyunsaturated Fat 1.5g\n" +
                    "Monounsaturated Fat 17g\n" +
                    "Cholesterol 214mg\n" +
                    "Sodium 115mg\n" +
                    "Potassium 698mg\n" +
                    "Total Carbohydrates 0g\n" +
                    "Dietary Fiber 0g\n" +
                    "Sugars 0g\n" +
                    "Protein 58g\n" +
                    "Vitamin A 0%\n" +
                    "Vitamin C 0%\n" +
                    "Calcium 2.9%\n" ;
        }
        else if(foodName.trim().equalsIgnoreCase(guava)){
            desc ="Guava is a common tropical fruit cultivated in many tropical and subtropical regions. Psidium guajava is a small tree in the myrtle family." +
                    "Psidium guajava, the common guava, yellow guava, or lemon guava, is an evergreen shrub.\n" +
                    "Scientific name: Psidium guajava. 100 grams contains:\n\n" +
                    "Calories 68\n" +
                    "Total Fat 1 g\t1%\n" +
                    "Saturated fat 0.3 g\t1%\n" +
                    "Polyunsaturated fat 0.4 g\t\n" +
                    "Monounsaturated fat 0.1 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 2 mg\t0%\n" +
                    "Potassium 417 mg\t11%\n" +
                    "Total Carbohydrate 14 g\t4%\n" +
                    "Dietary fiber 5 g\t20%\n" +
                    "Sugar 9 g\t\n" +
                    "Protein 2.6 g\t5%\n" +
                    "Vitamin A\t12%\n" +
                    "Vitamin C\t380%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t1%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t5%";
        }
        else if(foodName.trim().equalsIgnoreCase(terere)){
            desc ="Amaranthus is a cosmopolitan genus of annual or short-lived perennial plants collectively known as amaranths. " +
                    "Some amaranth species are cultivated as leaf vegetables, pseudocereals, and ornamental plants.\n\n" +
                    "Energy: 371.4 Calories (per 100 g)\n" +
                    "Protein: 14 g (per 100 g)\n" +
                    "Calcium: 159 mg (per 100 g)\n" +
                    "Iron: 7.6 mg (per 100 g)\n" +
                    "Potassium: 508 mg (per 100 g)\n" +
                    "Scientific name: Amaranthus";
        }
        else if(foodName.trim().equalsIgnoreCase(arrowrootLeaves)){
            desc ="Arrowroot is a starch obtained from the rhizomes of several tropical plants, traditionally Maranta arundinacea, " +
                    "but also Florida arrowroot from Zamia integrifolia, and tapioca from cassava, which is often labelled as arrowroot.\n" +
                    "100 grams contains: \n\n" +
                    "Calories 65\n" +
                    "Total Fat 0.2 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 26 mg\t1%\n" +
                    "Potassium 454 mg\t12%\n" +
                    "Total Carbohydrate 13 g\t4%\n" +
                    "Dietary fiber 1.3 g\t5%\n" +
                    "Protein 4.2 g\t8%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t3%\n" +
                    "Calcium\t0%\n" +
                    "Iron\t12%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t15%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t6%\n";
        }
        else if(foodName.trim().equalsIgnoreCase(nderema)){
            desc ="Basella alba is an edible perennial vine in the family Basellaceae. It is found in tropical Asia and Africa where it is " +
                    "widely used as a leaf vegetable. Scientific name: Malabar spinach\n." +
                    "100 grams contains:\n\n" +
                    "Calories 19\n" +
                    "Total Fat 0.3 g\t0%\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 24 mg\t1%\n" +
                    "Potassium 510 mg\t14%\n" +
                    "Total Carbohydrate 3.4 g\t1%\n" +
                    "Protein 1.8 g\t3%\n" +
                    "Vitamin A\t160%\n" +
                    "Vitamin C\t170%\n" +
                    "Calcium\t10%\n" +
                    "Iron\t6%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t10%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t16%";
        }
        else if(foodName.trim().equalsIgnoreCase(beanLeaves)){
            desc ="The vegetable leaves may be eaten when cooked or fresh, with cooked leaves being the preferred method for taste" +
                    "Serving Size of 100 grams (100g) contains: \n\n" +
                    "Calories 74\n" +
                    "Calories from Fat 9.9 (13.4%)\n" +
                    "Total Fat 1.1g\t-\n" +
                    "Saturated fat 0.3g\t-\n" +
                    "Monounsaturated fat 0.3g\t-\n" +
                    "Polyunsaturated fat 0.2g\t-\n" +
                    "Sodium 9mg\t1%\n" +
                    "Potassium 176mg\t-\n" +
                    "Carbohydrates 14.1g\t-\n" +
                    "Net carbs 14.1g\t-\n" +
                    "Protein 5.9g\t\n" +
                    "Vitamins and minerals\n" +
                    "Vitamin A 405μg\t45%\n" +
                    "Vitamin A IU 8090IU\t-\n" +
                    "Vitamin B6 0.2mg\t18%\n" +
                    "Vitamin B12 0μg\t0%\n" +
                    "Vitamin C 45mg\t75%\n" +
                    "Vitamin D 0μg\t0%\n" +
                    "Vitamin D IU 0IU\t-\n" +
                    "Calcium 224mg\t23%\n" +
                    "Iron 4mg\t50%\n" +
                    "Magnesium 8mg\t3%\n" +
                    "Phosphorus 63mg\t7%\n" +
                    "Zinc 1.3mg\t9%\n" +
                    "Copper 0.5mg\t23%\n" +
                    "Manganese 1.4mg\t69%\n" +
                    "Selenium 0.9μg\t2%\n" +
                    "Retinol 0μg\t-\n" +
                    "Thiamine 0.8mg\t56%\n" +
                    "Riboflavin 0.6mg\t36%\n" +
                    "Niacin 3.5mg\t18%\n" +
                    "Folate 16μg\t4%\n" +
                    "Water 76.9g\t";
        }
        else if(foodName.trim().equalsIgnoreCase(managu)){
            desc ="Black nightshade is highly variable, and poisonous plant experts advise to avoid eating the berries unless they are a known edible strain. " +
                    "\nThe toxins in S. nigrum are most concentrated in the unripe green berries, and immature fruit should be treated as toxic." +
                    "\nAfrican nightshades are several species of plants in the section Solanum of the genus Solanum, that are " +
                    "commonly consumed as leafy vegetables and herbs. \n" +
                    "100 g fresh weight leaves of African nightshade consist of: \n\n" +
                    "87.2 g water\n" +
                    "1.0 mg iron\n" +
                    "4.3 g protein\n" +
                    "38 kcalories\n" +
                    "5.7 g carbohydrates\n" +
                    "1.4 g fibre\n" +
                    "442 mg calcium\n" +
                    "20 mg ascorbic acid\n" +
                    "3660 μg ß-Carotene\n" +
                    "75 mg phosphorus\n" +
                    "0.59 mg riboflavin";
        }
        else if(foodName.trim().equalsIgnoreCase(cassavaLeaves)){
            desc ="Cassava leaves are a rich source of protein, minerals, and vitamins. Detoxified cassava leaves could serve as a safe nutrient" +
                    " source. \nCassava is mainly grown for its roots whereas leaves are mostly considered as a byproduct." +
                    "Processed Cassava Leaves\n" +
                    "For a Serving Size of 1 serving (110g)\n\n" +
                    "Calories 49.5\n" +
                    "Calories from Fat 18 (36.4%)\n" +
                    "Total Fat 2g\t-\n" +
                    "Sodium 0mg\t0%\n" +
                    "Carbohydrates 50g\t-\n" +
                    "Net carbs 49g\t-\n" +
                    "Sugar 0g\t-\n" +
                    "Fiber 1g\t4%\n" +
                    "Protein 8g\t\n" +
                    "Vitamin A IU 12500.4IU\t-\n" +
                    "Vitamin C 1.2mg\t3%\n" +
                    "Calcium 19.8mg\t2%\n" +
                    "Iron 4.5mg\t57%\n" +
                    "Fatty acids\n" +
                    "Amino acids";
        }
        else if(foodName.trim().equalsIgnoreCase(kunde)){
            desc ="Cowpeas also called black eyed peas usually cultivated for its edible beans but few grow it as a vegetable. The tender leaves and young " +
                    "pods are used for preparing nutritious vegetable dishes. \n\nIn contrary to other leafy vegetables, cowpeas requires less maintenance. " +
                    "Findings from a study indicated that cowpea leaves contained:\n\n" +
                    "Protein (34.91%)\n" +
                    "low glycemic index carbs (31.11%)\n" +
                    "Prebiotics (19.46%)\n" +
                    "Fat (5.42%)\n" +
                    "Iron (65.21 mg)\n" +
                    "Calcium (1.62 g)\n" +
                    "Phosphorus (0.56g)\n" +
                    "Magnesium (1.66 g)\n" +
                    "Potassium (13.445 g)\n" +
                    "Sodium (2.22 g).";
        }
        else if(foodName.trim().equalsIgnoreCase(mitoo)){
            desc ="Crotalaria is a genus of flowering plants in the legume family Fabaceae commonly known as rattlepods.\n" +
                    "The genus includes about 500 species of herbaceous plants and shrubs" +
                    "";
        }
        else if(foodName.trim().equalsIgnoreCase(mrenda)){
            desc ="The leaves are rich in folate, beta-carotene, iron, calcium, vitamin C and more than 32 vitamins, minerals and trace elements. " +
                    "The plant has a potent antioxidant activity with a significant α-tocopherol equivalent vitamin E";
        }
        else if(foodName.trim().equalsIgnoreCase(seveve)){
            desc ="Fluted pumpkin leaves are greenish leafy vegetables grown in Nigeria and other West African countries. " +
                    "They are edible when boiled or cooked with foods; it can also be taken as a vegetable salad or as juice when mashed as fresh leaves and " +
                    "the juice extracted." +
                    "\n\nPumpkin leaves are high in essential vitamins such as A and C. \nWhile vitamin A improves eyesight and promotes healthy skin and hair, " +
                    "vitamin C helps in healing wounds and forming scar tissue, as well as maintaining healthy bones, skin, and teeth" +
                    "";
        }
        else if(foodName.trim().equalsIgnoreCase(pumpkin)){
            desc ="Pumpkin is a cultivar of winter squash that is round with smooth, slightly ribbed skin, and is most often deep yellow to " +
                    "orange in coloration. \nThe thick shell contains the seeds and pulp. 100 grams contains:\n\n" +
                    "Calories 26\n" +
                    "Total Fat 0.1 g\t0%\n" +
                    "Saturated fat 0.1 g\t0%\n" +
                    "Polyunsaturated fat 0 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 1 mg\t0%\n" +
                    "Potassium 340 mg\t9%\n" +
                    "Total Carbohydrate 7 g\t2%\n" +
                    "Dietary fiber 0.5 g\t2%\n" +
                    "Sugar 2.8 g\t\n" +
                    "Protein 1 g\t2%\n" +
                    "Vitamin A\t170%\n" +
                    "Vitamin C\t15%\n" +
                    "Calcium\t2%\n" +
                    "Iron\t4%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t3%";
        }
        else if(foodName.trim().equalsIgnoreCase(saga)){
            desc ="The leaves are eaten as a cooked green vegetable, have a mildly bitter taste and contain 5% protein, 6% carbohydrates and are high in " +
                    "vitamins A and C, calcium, phosphorus and iron. \nSpider plant is used as a vegetable, and as such adds important nutrients to the " +
                    "diet in rural areas of East and Southern Africa" +
                    "";
        }
        else if(foodName.trim().equalsIgnoreCase(stingingNettleLeaves)){
            desc =" stinging nettle leaves are eaten as a cooked vegetable. \nIn manufacturing, stinging nettle extract is used as an ingredient in hair and " +
                    "skin products.";
        }
        else if(foodName.trim().equalsIgnoreCase(sweetPotatoLeaves)){
            desc ="Along with the tubers of sweet potato, the leaves are also edible. Besides its delicious taste It is flavorful with some bitter taste. The leaves " +
                    "are consumed and cooked as spinach. \nIt is a great source of antioxidant, Vitamin C, A, thiamin, riboflavin," +
                    " niacin and folic acid. 100 grams contains:\n\n" +
                    "Calories 41\n" +
                    "Total Fat 0.3 g\t0%\n" +
                    "Saturated fat 0.1 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 7 mg\t0%\n" +
                    "Potassium 312 mg\t8%\n" +
                    "Total Carbohydrate 7 g\t2%\n" +
                    "Dietary fiber 1.9 g\t7%\n" +
                    "Sugar 5 g\t\n" +
                    "Protein 2.2 g\t4%\n" +
                    "Vitamin A\t58%\n" +
                    "Vitamin C\t2%\n" +
                    "Calcium\t3%\n" +
                    "Iron\t3%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t10%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t12%";
        }
        else if(foodName.trim().equalsIgnoreCase(jabuticaba)){
            desc ="Plinia cauliflora, the Brazilian grapetree, jaboticaba or jabuticaba, is a tree in the family Myrtaceae, native to Minas Gerais, Goiás and São Paulo states in Brazil. " +
                    "Related species in the genus Myrciaria, often referred to by the same common names, are native to Brazil, Argentina, Paraguay, Peru and Bolivia" +
                    "Jaboticaba is a fruit that is low in fat, low in calories and low in carbohydrates. " +
                    "\n\nIt is a rich source of vitamin C and also contains other vitamins " +
                    "like vitamin E, thiamin, niacin, riboflavin and folic acid. \nMinerals like calcium, potassium, magnesium, iron, phosphorus, copper, manganese and zinc " +
                    "are also present in this fruit. In addition to vitamins and minerals, Jaboticaba is also a good source of several amino acids, fatty acids and many " +
                    "powerful antioxidants that have anti-cancer and anti-inflammatory properties.";
        }
        else if(foodName.trim().equalsIgnoreCase(jackFruit)){
            desc ="The jackfruit, also known as jack tree, is a species of tree in the fig, mulberry, and breadfruit family. Its origin is in the region " +
                    "between the Western Ghats of southern India and the rainforests of Malaysia" +
                    "One cup of sliced fruit provides the following nutrients:\n\n" +
                    "Calories: 155\n" +
                    "Carbs: 40 grams\n" +
                    "Fiber: 3 grams\n" +
                    "Protein: 3 grams\n" +
                    "Vitamin A: 10% of the RDI\n" +
                    "Vitamin C: 18% of the RDI\n" +
                    "Riboflavin: 11% of the RDI\n" +
                    "Magnesium: 15% of the RDI\n" +
                    "Potassium: 14% of the RDI\n" +
                    "Copper: 15% of the RDI\n" +
                    "Manganese: 16% of the RDI";
        }
        else if(foodName.trim().equalsIgnoreCase(juice)){
            desc ="Juice is a drink made from the extraction or pressing of the natural liquid contained in fruit and vegetables. It can also refer to " +
                    "liquids that are flavored with concentrate or other biological food sources, such as meat or seafood, such as clam juice";
        }
        else if(foodName.trim().equalsIgnoreCase(lemon)){
            desc ="The lemon, Citrus limon, is a species of small evergreen tree in the flowering plant family Rutaceae, native to South Asia, primarily North eastern India" +
                    "Lemons are high in vitamin C, fiber, and various beneficial plant compounds. \n\nThese nutrients are responsible for several health benefits. " +
                    "Scientific name: Citrus × limon. 100 grams contains:\n\n" +
                    "Calories 29\n" +
                    "Total Fat 0.3 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 2 mg\t0%\n" +
                    "Potassium 138 mg\t3%\n" +
                    "Total Carbohydrate 9 g\t3%\n" +
                    "Dietary fiber 2.8 g\t11%\n" +
                    "Sugar 2.5 g\t\n" +
                    "Protein 1.1 g\t2%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t88%\n" +
                    "Calcium\t2%\n" +
                    "Iron\t3%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t2%";
        }
        else if(foodName.trim().equalsIgnoreCase(lime)){
            desc ="A lime, known as dayap in the Philippines, is a citrus fruit, which is typically round, green in color, 3–6 centimetres in diameter, " +
                    "and contains acidic juice vesicles. Scientific name: Citrus × aurantiifolia. 100 grams contains\n\n" +
                    "Calories 30\n" +
                    "Total Fat 0.2 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 2 mg\t0%\n" +
                    "Potassium 102 mg\t2%\n" +
                    "Total Carbohydrate 11 g\t3%\n" +
                    "Dietary fiber 2.8 g\t11%\n" +
                    "Sugar 1.7 g\t\n" +
                    "Protein 0.7 g\t1%\n" +
                    "Vitamin A\t1%\tVitamin C\t48%\n" +
                    "Calcium\t3%\tIron\t3%\n" +
                    "Vitamin D\t0%\tVitamin B-6\t0%\n" +
                    "Cobalamin\t0%\tMagnesium\t1%";
        }
        else if(foodName.trim().equalsIgnoreCase(liver)){
            desc ="The liver of mammals, fowl, and fish is commonly eaten as food by humans. \nDomestic pig, lamb, calf, ox, chicken, goose, and cod livers " +
                    "are widely available from butchers and supermarkets while stingray and burbot" +
                    " livers are common in some European countries. 100 grams contains:\n\n" +
                    "Calories 165\n" +
                    "Total Fat 4.4 g\t6%\n" +
                    "Saturated fat 1.4 g\t7%\n" +
                    "Polyunsaturated fat 1.1 g\t\n" +
                    "Monounsaturated fat 0.6 g\t\n" +
                    "Cholesterol 355 mg\t118%\n" +
                    "Sodium 49 mg\t2%\n" +
                    "Potassium 150 mg\t4%\n" +
                    "Total Carbohydrate 3.8 g\t1%\n" +
                    "Dietary fiber 0 g\t0%\n" +
                    "Protein 26 g\t52%\n" +
                    "Vitamin A\t359%\n" +
                    "Vitamin C\t39%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t99%\n" +
                    "Vitamin B-6\t30%\n" +
                    "Cobalamin\t311%\n" +
                    "Magnesium\t3%\t\t";
        }
        else if(foodName.trim().equalsIgnoreCase(mabuyu)){
            desc ="Baobab fruit nutrition\n" +
                    "Baobab powder is renowned for its high vitamin C content, with one tablespoon (10g of baobab) providing just over half of the RDA of " +
                    "vitamin C for adults. \nIt also contributes minerals like potassium, calcium and magnesium as well as certain B vitamins, fibre and " +
                    "protein";
        }
        else if(foodName.trim().equalsIgnoreCase(roastedMaize)){
            desc ="Maize, also known as corn, is a cereal grain first domesticated by indigenous peoples in southern Mexico about 10,000 years ago. " +
                    "\n\nThe leafy stalk of the plant produces pollen inflorescences and separate ovuliferous inflorescences called ears that yield " +
                    "kernels or seeds, which are fruits. Scientific name: Zea mays. " +
                    "For a Serving Size of 0.67 cup (90g)\n" +
                    "Calories 100\n" +
                    "Calories from Fat 9 (9%)\n" +
                    "Total Fat 1g\t-\n" +
                    "Sodium 0mg\t0%\n" +
                    "Carbohydrates 22g\t-\n" +
                    "Net carbs 19g\t-\n" +
                    "Fiber 3g\t12%\n" +
                    "Glucose 2g\n" +
                    "Protein 3g\t\n" +
                    "Vitamins and minerals\n" +
                    "Vitamin A 0μg\t0%\n" +
                    "Vitamin C 9mg\t15%\n" +
                    "Calcium 0mg\t0%\n" +
                    "Iron 0.2mg\t3%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(boiledMaize)){
            desc ="Maize, also known as corn, is a cereal grain first domesticated by indigenous peoples in southern Mexico about 10,000 " +
                    "years ago. \n\nThe leafy stalk of the plant produces pollen inflorescences and separate ovuliferous inflorescences called " +
                    "ears that yield kernels or seeds, which are fruits. Scientific name: Zea mays. " +
                    "3.5 ounces (100 grams) of boiled yellow corn contains:\n\n" +
                    "Calories: 96\n" +
                    "Water: 73%\n" +
                    "Protein: 3.4 grams\n" +
                    "Carbs: 21 grams\n" +
                    "Sugar: 4.5 grams\n" +
                    "Fiber: 2.4 grams\n" +
                    "Fat: 1.5 grams"
            ;}
        else if(foodName.trim().equalsIgnoreCase(mandazi)){
            desc ="Mandazi, is a form of fried bread that originated on the Swahili Coast. It is also known as bofrot or puff puff in " +
                    "Western African countries such as Ghana and Nigeria. \n\nIt is one of the principal dishes in the cuisine of " +
                    "the Swahili people who inhabit the Coastal Region of Kenya and Tanzania. A small piece (3\" diameter) (31g) contains:\n\n" +
                    "Calories from Fat 29\n" +
                    "Calories 99\n" +
                    "Total Fat 3.2g\n" +
                    "Saturated Fat 1.9g\n" +
                    "Trans Fat 0.1g\n" +
                    "Cholesterol 18mg\n" +
                    "odium 100mg\n" +
                    "Potassium 45mg\n" +
                    "Total Carbohydrates 15g\n" +
                    "Dietary Fiber 0.5g\n" +
                    "Sugars 3.9g\n" +
                    "Protein 2.4g\n" +
                    "Vitamin A 2%\n" +
                    "Vitamin C 0%\n" +
                    "Calcium 6%\n" +
                    "Iron 5%\n"
            ;}
        else if(foodName.trim().equalsIgnoreCase(mango)){
            desc ="A mango is a stone fruit produced from numerous species of tropical trees belonging to the flowering plant " +
                    "genus Mangifera, cultivated mostly for their edible fruit. \n\nMost of these species are found in nature as wild " +
                    "mangoes. Scientific name: Mangifera indica. 100 grams contains:\n\n" +
                    "Calories 60\n" +
                    "Total Fat 0.4 g\t0%\n" +
                    "Saturated fat 0.1 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0.1 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 1 mg\t0%\n" +
                    "Potassium 168 mg\t4%\n" +
                    "Total Carbohydrate 15 g\t5%\n" +
                    "Dietary fiber 1.6 g\t6%\n" +
                    "Sugar 14 g\t\n" +
                    "Protein 0.8 g\t1%\n" +
                    "Vitamin A\t21%\n" +
                    "Vitamin C\t60%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t1%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t2%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(mashedPotato)){
            desc ="Mashed potato or mashed potatoes, colloquially known as mash, is a dish of mashing boiled potatoes, usually with added milk, butter, salt and " +
                    "pepper. \n\nIt is generally served as a side dish to meat or vegetables. When the potatoes are only roughly mashed, " +
                    "they are sometimes called smashed potatoes. 100 grams contains:\n\n" +
                    "Calories 88\n" +
                    "Total Fat 2.8 g\t4%\n" +
                    "Saturated fat 0.6 g\t3%\n" +
                    "Polyunsaturated fat 1.3 g\t\n" +
                    "Monounsaturated fat 0.7 g\t\n" +
                    "Trans fat regulation 0.1 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 306 mg\t12%\n" +
                    "Potassium 286 mg\t8%\n" +
                    "Total Carbohydrate 15 g\t5%\n" +
                    "Dietary fiber 1.3 g\t5%\n" +
                    "Sugar 0.5 g\t\n" +
                    "Protein 1.7 g\t3%\n" +
                    "Vitamin A\t3%\n" +
                    "Vitamin C\t0%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t1%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t1%\n" +
                    "Magnesium\t3%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(miracleFruit)){
            desc ="An evergreen shrub that grows in West Africa. The berry of the miracle fruit plant is used as medicine. " +
                    "People take miracle fruit to treat diabetes and correct chemotherapy-related taste disturbances. " +
                    "\n\nIn foods, miracle fruit is used as a low-calorie sugar-free sweetener. " +
                    "Scientific name: Synsepalum dulcificum. " +
                    "Serving Size: berry of 12g contains:\n\n" +
                    "Calories from Fat 0.4\n" +
                    "Calories 3.8\n" +
                    "Total Fat 0% 0g\n" +
                    "Saturated Fat 0% 0g\n" +
                    "Trans Fat 0g\n" +
                    "Cholesterol 0% 0mg\n" +
                    "Sodium 0% 0.1mg\n" +
                    "Potassium 1% 18mg\n" +
                    "Total Carbohydrates 0% 0.9g\n" +
                    "Dietary Fiber 1% 0.2g\n" +
                    "Sugars 0.6g\n" +
                    "Protein 0.1g\n" +
                    "Vitamin A 0%\n" +
                    "Vitamin C 12%\n" +
                    "Calcium 0%\n" +
                    "Iron 0%\n"
            ;}
        else if(foodName.trim().equalsIgnoreCase(mukimo)){
            desc ="Mukimo (Irio) is a Kenyan meal (predominantly from communities living around Mount Kenya) prepared by mashing " +
                    "potatoes and green vegetables. \n\nIt may also include maize and beans. Mukimo is mostly served as an accompaniment " +
                    "for meat-based stew and nyama choma. Any serving largely contains:\n\n" +
                    "Carbs 66% 50g\n" +
                    "Fat 24% 8g\n" +
                    "Protein 11% 8g"
            ;}
        else if(foodName.trim().equalsIgnoreCase(mushroom)){
            desc ="A mushroom or toadstool is the fleshy, spore-bearing fruiting body of a fungus, typically produced above ground, on " +
                    "soil, or on its food source. Scientific name: Agaricus bisporus. 100 grams contains:\n\n" +
                    "Calories 22\n" +
                    "Total Fat 0.3 g\t0%\n" +
                    "Saturated fat 0.1 g\t0%\n" +
                    "Polyunsaturated fat 0.2 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 5 mg\t0%\n" +
                    "Potassium 318 mg\t9%\n" +
                    "Total Carbohydrate 3.3 g\t1%\n" +
                    "Dietary fiber 1 g\t4%\n" +
                    "Sugar 2 g\t\n" +
                    "Protein 3.1 g\t6%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t3%\n" +
                    "Calcium\t0%\n" +
                    "Iron\t2%\n" +
                    "Vitamin D\t1%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t2%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(muthokoi)){
            desc ="Muthokoi (maize without husks) is a dish among the Kamba community from the Eastern part of Kenya. It entails " +
                    "removing the outer skin of the maize grains by using a pestle and a wooden mortar. 100g contains:\n\n" +
                    "Calories 928\n" +
                    "Total Fat 18.8g\n" +
                    "Saturated Fat 2.7g\n" +
                    "Trans Fat 0g\n" +
                    "Polyunsaturated Fat 3.4g\n" +
                    "Monounsaturated Fat 11.2g\n" +
                    "Cholesterol 0mg\n" +
                    "Sodium 40% 968mg\n" +
                    "Potassium 113%3952mg\n" +
                    "55%Total Carbohydrates 164g\n" +
                    "147%Dietary Fiber 36.8g\n" +
                    "Sugars 28.8g\n" +
                    "Protein 40g\n" +
                    "Vitamin A\n" +
                    "Vitamin C\n" +
                    "Calcium\n" +
                    "Iron"
            ;}
        else if(foodName.trim().equalsIgnoreCase(mutton)){
            desc ="Lamb, hogget, and mutton, generically sheep meat, are the meat of domestic sheep, Ovis aries. A sheep in its " +
                    "first year is a lamb and its meat is also lamb. \n\nA sheep in its second year and its meat are hogget. " +
                    "Older sheep meat is mutton. Scientific name of sheep: Ovis aries. 100 grams\n" +
                    "Calories 294\n" +
                    "Total Fat 21 g\t32%\n" +
                    "Saturated fat 9 g\t45%\n" +
                    "Polyunsaturated fat 1.5 g\t\n" +
                    "Monounsaturated fat 9 g\t\n" +
                    "Cholesterol 97 mg\t32%\n" +
                    "Sodium 72 mg\t3%\n" +
                    "Potassium 310 mg\t8%\n" +
                    "Total Carbohydrate 0 g\t0%\n" +
                    "Dietary fiber 0 g\t0%\n" +
                    "Sugar 0 g\t\n" +
                    "Protein 25 g\t50%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t0%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t10%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t43%\n" +
                    "Magnesium\t5%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(olive)){
            desc ="The olive, known by the botanical name Olea europaea, meaning \"European olive\", is a species of small tree " +
                    "in the family Oleaceae, found traditionally in the Mediterranean Basin. Scientific name: Olea europaea.\n\n" +
                    "Nutrition facts\n" +
                    "Calories: 115.\n" +
                    "Water: 80%\n" +
                    "Protein: 0.8 grams.\n" +
                    "Carbs: 6.3 grams.\n" +
                    "Sugar: 0 grams.\n" +
                    "Fiber: 3.2 grams.\n" +
                    "Fat: 10.7 grams\n" +
                    "Saturated: 1.42 grams\n" +
                    "Monounsaturated: 7.89 grams\n" +
                    "Polyunsaturated: 0.91 grams."
            ;}
        else if(foodName.trim().equalsIgnoreCase(omena)){
            desc ="The silver cyprinid also known as the Lake Victoria sardine or mukene, Omena is a species of pelagic, " +
                    "freshwater ray-finned fish in the carp family, Cyprinidae from East Africa. It's the only member of the " +
                    "genus Rastrineobola. Scientific name: Rastrineobola argentea. \n\n" +
                    "It has the best types of proteins which are very essential in the human body. Has Omega 3-fatty acids which are good for the heart and brain. Pregnant mothers are advised to eat " +
                    "Omena regularly if they want to have healthy babies with strong . Omena is " +
                    "rich in Calcium, Dietary source of Vitamin D, May prevent Asthma in children and also a Source of Iron and Zinc"
            ;}
        else if(foodName.trim().equalsIgnoreCase(orange)){
            desc ="The orange is the fruit of various citrus species in the family Rutaceae; it primarily refers to Citrus × " +
                    "sinensis, which is also called sweet orange, to distinguish it from the related Citrus × aurantium, " +
                    "referred to as bitter orange. \n\nScientific name: Citrus X sinensis. 100 grams contains: \n\n" +
                    "Calories 47\n" +
                    "Total Fat 0.1 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 0 mg\t0%\n" +
                    "Potassium 181 mg\t5%\n" +
                    "Total Carbohydrate 12 g\t4%\n" +
                    "Dietary fiber 2.4 g\t9%\n" +
                    "Sugar 9 g\t\n" +
                    "Protein 0.9 g\t1%\n" +
                    "Vitamin A\t4%\tVitamin C\t88%\n" +
                    "Calcium\t4%\tIron\t0%\n" +
                    "Vitamin D\t0%\tVitamin B-6\t5%\n" +
                    "Cobalamin\t0%\tMagnesium\t2%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(pawpaw)){
            desc ="Papaya, pawpaw is the plant Carica papaya, one of the 22 accepted species in the genus Carica of the family Caricaceae." +
                    "Scientific name: Carica papaya. One small papaya (152 grams) contains:\n\n" +
                    "Calories: 59\n" +
                    "Carbohydrates: 15 grams\n" +
                    "Fiber: 3 grams\n" +
                    "Protein: 1 gram\n" +
                    "Vitamin C: 157% of the RDI\n" +
                    "Vitamin A: 33% of the RDI\n" +
                    "Folate (vitamin B9): 14% of the RDI\n" +
                    "Potassium: 11% of the RDI\n" +
                    "Trace amounts of calcium, magnesium and vitamins B1, B3, B5, E and K."
            ;}
        else if(foodName.trim().equalsIgnoreCase(peaches)){
            desc ="The peach is a deciduous tree native to the region of Northwest China between the Tarim Basin and the north " +
                    "slopes of the Kunlun Mountains, where it was first domesticated and cultivated. Scientific name: Prunus persica. " +
                    "100 grams contains: \n\n" +
                    "Calories 39\n" +
                    "Total Fat 0.3 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0.1 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 0 mg\t0%\n" +
                    "Potassium 190 mg\t5%\n" +
                    "Total Carbohydrate 10 g\t3%\n" +
                    "Dietary fiber 1.5 g\t6%\n" +
                    "Sugar 8 g\t\n" +
                    "Protein 0.9 g\t1%\n" +
                    "Vitamin A\t6%\n" +
                    "Vitamin C\t11%\n" +
                    "Calcium\t0%\n" +
                    "Iron\t1%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t0%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t2%";}
        else if(foodName.trim().equalsIgnoreCase(peanutStew)){
            desc ="Peanut soup or groundnut soup is a soup made from peanuts, often with various other ingredients.\n" +
                    "Amount Per Serving\n\n" +
                    "Calories\t245.7\n" +
                    "Total Fat\t7.5 g\n" +
                    "Saturated Fat\t1.2 g\n" +
                    "Polyunsaturated Fat\t2.2 g\n" +
                    "Monounsaturated Fat\t3.5 g\n" +
                    "Cholesterol\t0.0 mg\n" +
                    "Sodium\t819.0 mg\n" +
                    "Potassium\t475.1 mg\n" +
                    "Total Carbohydrate\t38.8 g\n" +
                    "Dietary Fiber\t9.8 g\n" +
                    "Sugars\t8.7 g\n" +
                    "Protein\t9.8 g\n" +
                    "Vitamin A\t210.8 %\n" +
                    "Vitamin B-12\t0.0 %\n" +
                    "Vitamin B-6\t17.3 %\n" +
                    "Vitamin C\t43.4 %\n" +
                    "Vitamin D\t0.0 %\n" +
                    "Vitamin E\t1.1 %\n" +
                    "Calcium\t8.6 %\n" +
                    "Copper\t11.8 %\n" +
                    "Folate\t8.5 %\n" +
                    "Iron\t12.4 %\n" +
                    "Magnesium\t12.1 %\n" +
                    "Manganese\t27.5 %\n" +
                    "Niacin\t12.3 %\n" +
                    "Pantothenic Acid\t8.2 %\n" +
                    "Phosphorus\t9.7 %\n" +
                    "Riboflavin\t4.3 %\n" +
                    "Selenium\t2.4 %\n" +
                    "Thiamin\t7.5 %\n" +
                    "Zinc\t5.0 %";}
        else if(foodName.trim().equalsIgnoreCase(mbaazi)){
            desc ="The pigeon pea, also known as pigeonpea, red gram, tur, pwa kongo in Haiti, guandú and frijol de palo in " +
                    "Ibero-America, or as gungo peas in Jamaica, is a perennial legume from the family Fabaceae. 100 grams contains:\n\n" +
                    "Calories 343\n" +
                    "Total Fat 1.5 g\t2%\n" +
                    "Saturated fat 0.3 g\t1%\n" +
                    "Polyunsaturated fat 0.8 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 17 mg\t0%\n" +
                    "Potassium 1,392 mg\t39%\n" +
                    "Total Carbohydrate 63 g\t21%\n" +
                    "Dietary fiber 15 g\t60%\n" +
                    "Protein 22 g\t44%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t0%\n" +
                    "Calcium\t13%\n" +
                    "Iron\t28%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t15%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t45%";
        }
        else if(foodName.trim().equalsIgnoreCase(peas)){
            desc ="Commonly known as minji. Small spherical seed or the seed-pod of the pod fruit Pisum sativum. Each pod contains several peas, which can " +
                    "be green or yellow. \n\nBotanically, pea pods are fruit, since they contain seeds and develop from the ovary of " +
                    "a flower. Scientific name: Pisum sativum. 100 grams contains:\n\n" +
                    "Calories 81\n" +
                    "Total Fat 0.4 g\t0%\n" +
                    "Saturated fat 0.1 g\t0%\n" +
                    "Polyunsaturated fat 0.2 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 5 mg\t0%\n" +
                    "Potassium 244 mg\t6%\n" +
                    "Total Carbohydrate 14 g\t4%\n" +
                    "Dietary fiber 5 g\t20%\n" +
                    "Sugar 6 g\t\n" +
                    "Protein 5 g\t10%\n" +
                    "Vitamin A\t15%\n" +
                    "Vitamin C\t66%\n" +
                    "Calcium\t2%\n" +
                    "Iron\t8%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t10%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t8%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(pilau)){
            desc ="Also Pilaf, pulao or polao is a rice dish or, in some regions, a wheat dish, whose recipe usually involves " +
                    "cooking in stock or broth, adding spices, and other ingredients such as vegetables or meat, and employing " +
                    "some technique for achieving cooked grains that do not adhere. \n\n100 grams of pilau contains(this varies depending with the ingredients used):\n" +
                    "Calories 359\n" +
                    "Total Fat 1.4 g\t2%\n" +
                    "Sodium 1,303 mg\t54%\n" +
                    "Potassium 188 mg\t5%\n" +
                    "Total Carbohydrate 76 g\t25%\n" +
                    "Dietary fiber 1.2 g\t4%\n" +
                    "Sugar 1.5 g\t\n" +
                    "Protein 10 g\t20%\n" +
                    "Vitamin A\t0%\n" +
                    "Calcium\t8%\n" +
                    "Iron\t13%\n" +
                    "Vitamin B-6\t20%\n" +
                    "Magnesium\t8%\t\t\n"
            ;}
        else if(foodName.trim().equalsIgnoreCase(pineapple)){
            desc ="The pineapple is a tropical plant with an edible fruit and the most economically significant plant in the " +
                    "family Bromeliaceae. Scientific name: Ananas comosus. 100 grams contains:\n\n" +
                    "Calories 50\n" +
                    "Total Fat 0.1 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 1 mg\t0%\n" +
                    "Potassium 109 mg\t3%\n" +
                    "Total Carbohydrate 13 g\t4%\n" +
                    "Dietary fiber 1.4 g\t5%\n" +
                    "Sugar 10 g\t\n" +
                    "Protein 0.5 g\t1%\n" +
                    "Vitamin A\t1%\n" +
                    "Vitamin C\t79%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t1%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t3%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(pizza)){
            desc ="Pizza is a savory dish of Italian origin consisting of a usually round, flattened base of leavened wheat-based " +
                    "dough topped with tomatoes, cheese, and often various other ingredients, which is then baked at a high " +
                    "temperature, traditionally in a wood-fired oven. \n\nA small pizza is sometimes called a pizzetta. 100 grams contains: \n\n" +
                    "Calories 266\n" +
                    "Total Fat 10 g\t15%\n" +
                    "Saturated fat 4.5 g\t22%\n" +
                    "Polyunsaturated fat 1.7 g\t\n" +
                    "Monounsaturated fat 2.6 g\t\n" +
                    "Trans fat regulation 0.2 g\t\n" +
                    "Cholesterol 17 mg\t5%\n" +
                    "Sodium 598 mg\t24%\n" +
                    "Potassium 172 mg\t4%\n" +
                    "Total Carbohydrate 33 g\t11%\n" +
                    "Dietary fiber 2.3 g\t9%\n" +
                    "Sugar 3.6 g\t\n" +
                    "Protein 11 g\t22%\n" +
                    "Vitamin A\t7%\n" +
                    "Vitamin C\t2%\n" +
                    "Calcium\t18%\n" +
                    "Iron\t13%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t6%\n" +
                    "Magnesium\t6%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(pomegranate)){
            desc ="The pomegranate is a fruit-bearing deciduous shrub in the family Lythraceae, subfamily Punicoideae, that " +
                    "grows between 5 and 10 m tall. Scientific name: Punica granatum. 100 grams contains:\n\n" +
                    "Calories 83\n" +
                    "Total Fat 1.2 g\t1%\n" +
                    "Saturated fat 0.1 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0.1 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 3 mg\t0%\n" +
                    "Potassium 236 mg\t6%\n" +
                    "Total Carbohydrate 19 g\t6%\n" +
                    "Dietary fiber 4 g\t16%\n" +
                    "Sugar 14 g\t\n" +
                    "Protein 1.7 g\t3%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t17%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t1%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t5%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t3%\n"
            ;}
        else if(foodName.trim().equalsIgnoreCase(pork)){
            desc ="Pork is the culinary name for the meat of a domestic pig. It is the most commonly consumed meat worldwide as at making this app (Nov 2020), " +
                    "with evidence of pig husbandry dating back to 5000 BC. 100 grams contains:\n" +
                    "Calories 242\n" +
                    "Total Fat 14 g\t21%\n" +
                    "Saturated fat 5 g\t25%\n" +
                    "Polyunsaturated fat 1.2 g\t\n" +
                    "Monounsaturated fat 6 g\t\n" +
                    "Cholesterol 80 mg\t26%\n" +
                    "Sodium 62 mg\t2%\n" +
                    "Potassium 423 mg\t12%\n" +
                    "Total Carbohydrate 0 g\t0%\n" +
                    "Dietary fiber 0 g\t0%\n" +
                    "Sugar 0 g\t\n" +
                    "Protein 27 g\t54%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t1%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t4%\n" +
                    "Vitamin D\t13%\n" +
                    "Vitamin B-6\t25%\n" +
                    "Cobalamin";}
        else if(foodName.trim().equalsIgnoreCase(rambutan)){
            desc ="he rambutan is a medium-sized tropical tree in the family Sapindaceae. The name also refers to the " +
                    "edible fruit produced by this tree." +
                    "One medium rambutan fruit contains:\n\n" +
                    "Calories: 7.\n" +
                    "Protein: Less than 1 gram.\n" +
                    "Fat: Less than 1 gram.\n" +
                    "Carbohydrates: 2 grams.\n" +
                    "Fiber: Less than 1 gram.\n" +
                    "Sugar: Less than 1 gram."
            ;}
        else if(foodName.trim().equalsIgnoreCase(samosa)){
            desc ="A samosa is a fried or baked pastry with a savory filling, such as spiced potatoes, onions, peas, cheese, " +
                    "beef and other meats, or lentils. \n\nIt may take different forms, including triangular, cone, or half-moon shapes, " +
                    "depending on the region. Has varied nutritional facts depending on ingredients."
            ;}
        else if(foodName.trim().equalsIgnoreCase(sausage)){
            desc ="This app focus on beef sausage. Amount Per 100 grams contains:\n\n" +
                    "Calories 346\n" +
                    "Total Fat 31 g\t47%\n" +
                    "Saturated fat 11 g\t55%\n" +
                    "Polyunsaturated fat 4 g\t\n" +
                    "Monounsaturated fat 14 g\t\n" +
                    "Cholesterol 76 mg\t25%\n" +
                    "Sodium 731 mg\t30%\n" +
                    "Potassium 253 mg\t7%\n" +
                    "Total Carbohydrate 0.7 g\t0%\n" +
                    "Dietary fiber 0 g\t0%\n" +
                    "Protein 14 g\t28%\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t3%\n" +
                    "Calcium\t1%\n" +
                    "Iron\t6%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t15%\n" +
                    "Cobalamin\t14%\n" +
                    "Magnesium\t3%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(shellfish)){
            desc ="Shellfish is a colloquial and fisheries term for exoskeleton-bearing aquatic invertebrates used as food, " +
                    "including various species of molluscs, crustaceans, and echinoderms. Shrimp of 100g, cooked contains (Sources include: USDA):\n\n" +
                    "Calories 99\n" +
                    "Total Fat 0.3 g\t0%\n" +
                    "Saturated fat 0.1 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Trans fat regulation 0 g\t\n" +
                    "Cholesterol 189 mg\t63%\n" +
                    "Sodium 111 mg\t4%\n" +
                    "Potassium 259 mg\t7%\n" +
                    "Total Carbohydrate 0.2 g\t0%\n" +
                    "Protein 24 g\t48%\n" +
                    "Vitamin A\t0%\tVitamin C\t0%\n" +
                    "Calcium\t7%\tIron\t2%\n" +
                    "Vitamin D\t0%\tVitamin B-6\t0%\n" +
                    "Cobalamin\t0%\tMagnesium\t9%";}
        else if(foodName.trim().equalsIgnoreCase(smokey)){
            desc ="There are several types of smokie out there. This app focus on beef smokie, with ingredients of: " +
                    "beef, water, glucose, salt, spices, sodium phosphate, garlic, onion, sodium erythorbate, sodium nitrite, mustard, smoke." +
                    "per 1 smokie (75G) contains:\n\n" +
                    "Calories 160\t\n" +
                    "Fat 11g\t17%\n" +
                    "Saturated 4.5g\t23%\n" +
                    "+ Trans 0g\t\n" +
                    "Cholesterol 45mg\t\n" +
                    "Sodium 640mg\t27%\n" +
                    "Carbohydrate 2g\t1%\n" +
                    "Fibre 0g\t0%\n" +
                    "Sugars 0g\t\n" +
                    "Protein 13g\t\n" +
                    "Vitamin A\t0%\n" +
                    "Vitamin C\t0%\n" +
                    "Calcium\t0%\n" +
                    "Iron\t10%";}
        else if(foodName.trim().equalsIgnoreCase(soda)){
            desc ="A soft drink is a drink that usually contains carbonated water, a sweetener, and a natural or artificial " +
                    "flavoring. \n\nThe sweetener may be a sugar, high-fructose corn syrup, fruit juice, a sugar substitute, or " +
                    "some combination of these. Has varied nutritional facts.\n" +
                    "We strongly advise against frequent intake of soft drinks mainly soda."
            ;}
        else if(foodName.trim().equalsIgnoreCase(spinach)){
            desc ="Spinach is a leafy green flowering plant native to central and western Asia. " +
                    "Scientific name: Spinacia oleracea. " +
                    "One cup of raw spinach contains:\n" +
                    "7 calories.\n" +
                    "0.86 grams (g) of protein.\n" +
                    "30 milligrams (mg) of calcium.\n" +
                    "0.81 g of iron.\n" +
                    "24 mg of magnesium.\n" +
                    "167 mg of potassium.\n" +
                    "2,813 interational units (IU) of Vitamin A.\n" +
                    "58 micrograms of folate."
            ;}
        else if(foodName.trim().equalsIgnoreCase(tangerine)){
            desc ="The tangerine is a group of orange-coloured citrus fruit consisting of hybrids of mandarin orange. The name was" +
                    " first used for fruit coming from Tangier, Morocco, described as a mandarin variety. " +
                    "Scientific name: Citrus reticulata. 100 grams contains:\n\n" +
                    "Calories 53\n" +
                    "Total Fat 0.3 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0.1 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 2 mg\t0%\n" +
                    "Potassium 166 mg\t4%\n" +
                    "Total Carbohydrate 13 g\t4%\n" +
                    "Dietary fiber 1.8 g\t7%\n" +
                    "Sugar 11 g\t\n" +
                    "Protein 0.8 g\t1%\n" +
                    "Vitamin A\t13%\tVitamin C\t44%\n" +
                    "Calcium\t3%\tIron\t1%\n" +
                    "Vitamin D\t0%\tVitamin B-6\t5%\n" +
                    "Cobalamin\t0%\tMagnesium\t3%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(viaziKarai)){
            desc ="Viazi Karai is a favourite from the coastal community. It is sold at almost every street corner in Mombasa, " +
                    "and the trend is picking up in various Kenayan towns. \n\nIt's first boiled and coated with batter which has" +
                    " wheat flour, this is the main difference with bhajia." +
                    "It's commonly prepared from irish potato and largely contains the nutritional facts of irish potato.";}

        else if(foodName.trim().equalsIgnoreCase(watermelon)){
            desc ="Watermelon is a plant species in the family Cucurbitaceae, a vine-like flowering plant. " +
                    "Scientific name: Citrullus lanatus. 100 grams contains: \n\n" +
                    "Calories 30\n" +
                    "Total Fat 0.2 g\t0%\n" +
                    "Saturated fat 0 g\t0%\n" +
                    "Polyunsaturated fat 0.1 g\t\n" +
                    "Monounsaturated fat 0 g\t\n" +
                    "Cholesterol 0 mg\t0%\n" +
                    "Sodium 1 mg\t0%\n" +
                    "Potassium 112 mg\t3%\n" +
                    "Total Carbohydrate 8 g\t2%\n" +
                    "Dietary fiber 0.4 g\t1%\n" +
                    "Sugar 6 g\t\n" +
                    "Protein 0.6 g\t1%\n" +
                    "Vitamin A\t11%\n" +
                    "Vitamin C\t13%\n" +
                    "Calcium\t0%\n" +
                    "Iron\t1%\n" +
                    "Vitamin D\t0%\n" +
                    "Vitamin B-6\t0%\n" +
                    "Cobalamin\t0%\n" +
                    "Magnesium\t2%"
            ;}
        else if(foodName.trim().equalsIgnoreCase(wingedTermite)){
            desc ="Termites are eusocial insects that are classified at the taxonomic rank of infraorder Isoptera, or " +
                    "as epifamily Termitoidae within the order Blattodea.\n\n" +
                    "Edible termites are nutritious with significant level of iron, zinc, protein and are moderate sources of omega 3 fatty acids " +
                    "with the proteins being highly digestible (Kinyuru et al., 2010)." +
                    "";}
        else if(foodName.trim().equalsIgnoreCase(wine)){
            desc ="A typical glass of wine (5 oz) contains about 120 calories. Calories don't differ very much " +
                    "depending on the type of wine. A glass of light, dry white wine (i.e. Vinho Verde, Picpoul, Trebbiano) at " +
                    "10% alcohol contains about 100 calories (85 from alcohol and 15 from carbohydrates)." +
                    "Red wine can have benefits for your heart, brain, and bones. \n" +
                    "Research has found that the polyphenols in red wine, like resveratrol, can increase levels of good " +
                    "cholesterol and protect the blood vessels' lining in your heart.";}
        else{
//            Toast.makeText(this,"Coming Soon", Toast.LENGTH_SHORT).show();
        }
    }
}
