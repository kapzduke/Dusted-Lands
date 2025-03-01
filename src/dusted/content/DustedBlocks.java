package dusted.content;

import arc.graphics.*;
import arc.struct.*;
import dusted.entities.bullet.*;
import dusted.graphics.*;
import dusted.type.*;
import dusted.world.blocks.defense.*;
import dusted.world.blocks.defense.turrets.*;
import dusted.world.blocks.distribution.*;
import dusted.world.blocks.environment.*;
import dusted.world.blocks.powder.*;
import dusted.world.blocks.power.*;
import dusted.world.blocks.production.*;
import dusted.world.blocks.storage.*;
import dusted.world.blocks.units.*;
import dusted.world.consumers.*;
import dusted.world.draw.*;
import dusted.world.meta.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.units.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

//TODO balancing, campaign stuff
public class DustedBlocks {
    public static Block
    //environment
    oreZircon, oreArsenic, oreAntimony, orchar, sulfur, volcanoZone,
    fallenStone, fallenMantle, decayedRock, riftRock, cavnenSediment, cavnenDusting, cavnenSilk,
    volstone, latite, scoria, stradrock, scorchedStradrock,
    fallenWall, decayedWall, cavnenWall, volstoneWall, scoriaWall, latiteWall, stradrockWall,
    //decor
    scoriaBoulder, latiteBoulder, stradrockBoulder,
    fallenBoulder, decayedBoulder, cavnenBoulder,
    volstoneBoulder, volSprout, weepingShrub,
    //defense
    zirconWall, zirconWallLarge, antimonyWall, antimonyWallLarge,
    decaySuppressor,
    //turrets, TODO balancing?
    abrade, sunder, bisect, scald, coruscate, strike, blight,
    cocaineDuo,
    //distribution
    transferLink, transferTower,
    //powder distribution
    chute, powderRouter, powderJunction, bridgeChute,
    denseChute, armoredChute,
    //power
    magmaticGenerator, slagCombustor,
    //crafters
    quartzExtractor, metaglassFurnace, siliconForge, rockwoolExtruder,
    //production
    pressureDrill, ignitionDrill,
    pneumaticFunnel, rotaryFunnel,
    //cores
    coreAbate, coreDissent, coreDecadence,
    //units
    //TODO rework this
    cavnenTerraConstructor, cavnenAerialConstructor,
    binaryRestructurer, ternaryRestructurer,
    //sandbox
    powderSource, powderVoid;

    public static void load() {
        //region environment
        volcanoZone = new VolcanoZone("volcano-zone");

        oreZircon = new OreBlock(DustedItems.zircon);

        oreArsenic = new OreBlock(DustedItems.arsenic);

        oreAntimony = new OreBlock(DustedItems.antimony);

        //TODO maybe dont use this for every single powder
        orchar = new PowderFloor("orchar-deposit") {{
            powderDrop = DustedPowders.orchar;
            attributes.set(Attribute.water, -0.7f);
        }};

        sulfur = new PowderFloor("sulfur-deposit") {{
            powderDrop = DustedPowders.sulfur;
            attributes.set(Attribute.water, -0.5f);
        }};

        //TODO is setting these attributes necessary?
        cavnenSediment = new Floor("cavnen-sediment") {{
            attributes.set(Attribute.oil, 1.2f);
            attributes.set(Attribute.water, -0.6f);
        }};

        cavnenDusting = new Floor("cavnen-dusting") {{
            attributes.set(Attribute.oil, 0.9f);
            attributes.set(Attribute.water, -0.65f);
        }};

        cavnenSilk = new Floor("cavnen-silk") {{
            drownTime = 360f;
            status = DustedStatusEffects.deteriorating;
            statusDuration = 3f * 60f;
            speedMultiplier = 0.1f;
            variants = 0;
            cacheLayer = DustedShaders.silkLayer;
            albedo = 0.7f;
        }};

        decayedRock = new Floor("decayed-rock");

        riftRock = new RiftFloor("rift-rock") {{
            blendGroup = decayedRock;
        }};

        fallenStone = new MistFloor("fallen-stone");

        fallenMantle = new MistFloor("fallen-mantle");

        volstone = new Floor("volstone");

        scoria = new Floor("scoria");

        latite = new Floor("latite");

        stradrock = new Floor("stradrock");

        scorchedStradrock = new Floor("scorched-stradrock");

        cavnenWall = new StaticWall("cavnen-wall") {{
            cavnenSediment.asFloor().wall = cavnenDusting.asFloor().wall = cavnenSilk.asFloor().wall = this;
        }};

        decayedWall = new StaticWall("decayed-wall") {{
            decayedRock.asFloor().wall = riftRock.asFloor().wall = this;
        }};

        fallenWall = new StaticWall("fallen-wall") {{
            fallenStone.asFloor().wall = fallenMantle.asFloor().wall = this;
        }};

        volstoneWall = new StaticWall("volstone-wall") {{
            attributes.set(DustedAttribute.rock, 0.8f);
        }};

        scoriaWall = new StaticWall("scoria-wall") {{
            attributes.set(DustedAttribute.rock, 1f);
        }};

        latiteWall = new StaticWall("latite-wall") {{
            attributes.set(DustedAttribute.rock, 1f);
        }};

        stradrockWall = new StaticWall("stradrock-wall") {{
            scorchedStradrock.asFloor().wall = this;
            attributes.set(DustedAttribute.rock, 1f);
        }};

        scoriaBoulder = new Prop("scoria-boulder") {{
            variants = 2;
            scoria.asFloor().decoration = this;
        }};

        latiteBoulder = new Prop("latite-boulder") {{
            variants = 2;
            latite.asFloor().decoration = this;
        }};

        stradrockBoulder = new Prop("stradrock-boulder") {{
            variants = 2;
            stradrock.asFloor().decoration = this;
        }};

        volSprout = new Prop("vol-sprout") {{
            variants = 2;
        }};

        weepingShrub = new Prop("weeping-shrub") {{
            variants = 2;
        }};

        cavnenBoulder = new Prop("cavnen-boulder") {{
            variants = 2;
            cavnenSediment.asFloor().decoration = cavnenDusting.asFloor().decoration = cavnenSilk.asFloor().decoration = this;
        }};

        decayedBoulder = new Prop("decayed-boulder") {{
            variants = 2;
            decayedRock.asFloor().decoration = riftRock.asFloor().decoration = this;
        }};

        fallenBoulder = new Prop("fallen-boulder") {{
            variants = 2;
            fallenStone.asFloor().decoration = fallenMantle.asFloor().decoration = this;
        }};

        //TODO maybe a tree for the fallen blocks?

        volstoneBoulder = new Prop("volstone-boulder") {{
            variants = 2;
            volstone.asFloor().decoration = this;
        }};

        //endregion
        //region crafters
        quartzExtractor = new PowderCrafter("quartz-extractor") {{
            requirements(Category.crafting, ItemStack.with(DustedItems.zircon, 60, DustedItems.antimony, 50));
            hasPower = true;
            size = 2;

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPowder(DustedPowders.quartzDust), new DrawDefault());
            outputPowder = new PowderStack(DustedPowders.quartzDust, 0.1f);
            craftTime = 120f;
            consumePower(1f);
            consumeItem(Items.sand, 1);

            researchCost = ItemStack.with(DustedItems.zircon, 100, DustedItems.arsenic, 50, DustedItems.antimony, 50);
        }};

        siliconForge = new PowderCrafter("silicon-forge") {{
            requirements(Category.crafting, ItemStack.with(DustedItems.arsenic, 120, DustedItems.antimony, 100, DustedItems.zircon, 80));
            size = 3;
            squareSprite = false;
            itemCapacity = 20;
            researchCost = ItemStack.with(DustedItems.zircon, 120, DustedItems.arsenic, 50, DustedItems.antimony, 50);

            outputItem = new ItemStack(Items.silicon, 15);
            craftTime = 300f;
            craftEffect = new MultiEffect(Fx.smeltsmoke, DustedFx.quartzSmokeCloud);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPowderAbsorb(DustedPowders.quartzDust), new DrawDefault(), new DrawGlowProgress(Pal.redLight));
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.08f;

            consumePower(2f);
            consume(new ConsumePowder(DustedPowders.quartzDust, 0.2f));
            consume(new ConsumePowder(DustedPowders.orchar, 0.1f));
        }};

        metaglassFurnace = new PowderCrafter("metaglass-furnace") {{
            requirements(Category.crafting, ItemStack.with(DustedItems.zircon, 120, Items.silicon, 70, DustedItems.antimony, 60));
            size = 3;
            squareSprite = false;

            outputItem = new ItemStack(Items.metaglass, 2);
            craftTime = 60f;
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawSpinFlame());
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            consumePower(1f);
            consume(new ConsumePowder(DustedPowders.quartzDust, 0.05f));
            consumeItem(DustedItems.antimony, 2);
        }};

        rockwoolExtruder = new DrawerWallCrafter("rockwool-extruder") {{
            requirements(Category.crafting, ItemStack.with(DustedItems.zircon, 120, Items.metaglass, 60, Items.silicon, 40));
            rotateDraw = false;
            size = 3;

            attribute = DustedAttribute.rock;
            output = DustedItems.rockwool;
            drillTime = 120f;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.slag),
                    new DrawDefault(),
                    new DrawSideExtractor()
            );

            consumePower(0.8f);
            consumeLiquid(Liquids.slag, 0.1f);
        }};

        //endregion
        //region distribution
        transferLink = new TransferLink("transfer-link") {{
            requirements(Category.distribution, ItemStack.with(DustedItems.zircon, 3));
            squareSprite = false;
            researchCost = ItemStack.with(DustedItems.zircon, 5);
        }};

        transferTower = new TransferLink("transfer-tower") {{
            requirements(Category.distribution, ItemStack.with(DustedItems.zircon, 10, DustedItems.arsenic, 10, Items.silicon, 5));
            squareSprite = false;
            size = 2;
            linkRange = 160f;
            maxLinks = 10;
            itemCapacity = 20;
        }};
        //endregion
        //region powder
        chute = new Chute("chute") {{
            requirements(Category.distribution, ItemStack.with(DustedItems.zircon, 1));
            health = 40;
        }};

        denseChute = new Chute("dense-chute") {{
            requirements(Category.distribution, BuildVisibility.hidden, ItemStack.with());
            powderPressure = 1.03f;
            powderCapacity = 16f;
            health = 100;
        }};

        armoredChute = new ArmoredChute("armored-chute") {{
            requirements(Category.distribution, ItemStack.with(DustedItems.zircon, 2, DustedItems.antimony, 1));
            powderPressure = 1.03f;
            powderCapacity = 16f;
            health = 220;
        }};

        powderRouter = new PowderRouter("powder-router") {{
            requirements(Category.distribution, ItemStack.with(DustedItems.zircon, 3));
        }};

        powderJunction = new PowderJunction("powder-junction") {{
            requirements(Category.distribution, ItemStack.with(DustedItems.zircon, 3, DustedItems.arsenic, 2));
        }};

        bridgeChute = new PowderBridge("bridge-chute") {{
            requirements(Category.distribution, ItemStack.with(DustedItems.zircon, 8, DustedItems.arsenic, 4));
            fadeIn = moveArrows = false;
            arrowSpacing = 6f;
            range = 4;
            hasPower = false;
        }};
        //endregion
        //region production
        //TODO maybe this shouldnt be a burst drill
        pressureDrill = new BurstDrill("pressure-drill") {{
            requirements(Category.production, ItemStack.with(DustedItems.zircon, 20));
            drillTime = 60f * 8f;
            size = 3;
            tier = 3;
            squareSprite = false;
            hasPower = true;
            drillEffect = new MultiEffect(Fx.mineBig, Fx.explosion);
            itemCapacity = 20;
            arrows = 2;
            arrowSpacing = 3f;
            baseArrowColor = Color.valueOf("726c6a");
            researchCost = ItemStack.with(DustedItems.zircon, 10);

            consumePower(20f / 60f);
        }};

        ignitionDrill = new PowderBurstDrill("ignition-drill") {{
            requirements(Category.production, ItemStack.with(DustedItems.zircon, 60, DustedItems.arsenic, 40));
            drillTime = 60f * 8f;
            size = 4;
            tier = 5;
            squareSprite = false;
            hasPower = true;
            drillEffect = new MultiEffect(Fx.mineHuge, Fx.explosion, DustedFx.mineIgnite);
            shake = 4f;
            itemCapacity = 40;
            arrows = 2;
            arrowSpacing = 4f;
            baseArrowColor = Color.valueOf("726c6a");

            researchCost = ItemStack.with(DustedItems.zircon, 20, DustedItems.arsenic, 10);

            consumePower(1f);
            consume(new ConsumePowder(DustedPowders.orchar, 0.1f));
        }};

        //TODO maybe it should just be a 2x2 block instead
        pneumaticFunnel = new Funnel("pneumatic-funnel") {{
            requirements(Category.production, ItemStack.with(DustedItems.zircon, 10));
            powderCapacity = 20f;
            squareSprite = false;
        }};

        rotaryFunnel = new Funnel("rotary-funnel") {{
            requirements(Category.production, ItemStack.with(Items.metaglass, 40, DustedItems.zircon, 20));
            size = 2;
            hasPower = true;
            powderCapacity = 40f;
            funnelAmount = 1f;
            consumePower(0.2f);
            extractEffect = DustedFx.funnelExtract;
            squareSprite = false;
        }};
        //endregion
        //region power
        magmaticGenerator = new FilterTileGenerator("magmatic-generator") {{
            requirements(Category.power, ItemStack.with(DustedItems.zircon, 50));
            size = 2;
            floating = true;
            ambientSound = Sounds.hum;
            filter = f -> f.liquidDrop == Liquids.slag ? f.liquidMultiplier : 0f;
            generateEffect = Fx.incinerateSlag;
            outputLiquid = new LiquidStack(Liquids.slag, 5f / 60f);
            liquidCapacity = 20f;
            powerProduction = 0.5f;
            researchCost = ItemStack.with(DustedItems.zircon, 10);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.slag, 1.25f), new DrawDefault());
        }};
        //endregion
        //region defense
        zirconWall = new Wall("zircon-wall") {{
            requirements(Category.defense, ItemStack.with(DustedItems.zircon, 6));
            health = 280;
        }};

        zirconWallLarge = new Wall("zircon-wall-large") {{
            requirements(Category.defense, ItemStack.mult(zirconWall.requirements, 4));
            scaledHealth = 280;
            size = 2;
        }};

        antimonyWall = new Wall("antimony-wall") {{
            requirements(Category.defense, ItemStack.with(DustedItems.antimony, 6));
            health = 440;
        }};

        antimonyWallLarge = new Wall("antimony-wall-large") {{
            requirements(Category.defense, ItemStack.mult(antimonyWall.requirements, 4));
            scaledHealth = 440;
            size = 2;
        }};

        decaySuppressor = new DecaySuppressor("decay-suppressor") {{
           requirements(Category.effect, ItemStack.with(DustedItems.zircon, 50));
           size = 3;
           consumePower(1f);
           researchCost = ItemStack.with(DustedItems.zircon, 30);
        }};
        //endregion
        //region turrets
        abrade = new ItemTurret("abrade") {{
            requirements(Category.turret, ItemStack.with(DustedItems.zircon, 80, DustedItems.arsenic, 60));
            size = 2;
            scaledHealth = 240f;
            reload = 12f;
            recoil = 4f;
            range = 75f;
            shootSound = Sounds.pew;
            rotateSpeed = 18f;
            outlineColor = DustedPal.darkerWarmMetal;
            drawer = new DrawTurret("decayed-");

            ammo(
                    DustedItems.zircon, new ShrapnelBulletType() {{
                        hitEffect = Fx.hitBulletSmall;
                        smokeEffect = Fx.shootSmallSmoke;
                        shootEffect = DustedFx.shootPinkShrapnel;
                        status = DustedStatusEffects.deteriorating;
                        statusDuration = 8 * 60f;
                        fromColor = DustedPal.decayingYellow;
                        toColor = DustedPal.decayingYellowBack;
                        length = 75f;
                        damage = 12f;
                        width = 16f;
                        serrations = 0;
                    }}
            );

            coolant = consumeCoolant(0.1f);
            researchCostMultiplier = 0.1f;
        }};

        scald = new PowderTurret("scald") {{
            requirements(Category.turret, ItemStack.with(DustedItems.zircon, 70, DustedItems.arsenic, 60, DustedItems.antimony, 40));
            size = 2;
            scaledHealth = 260f;
            reload = 40f;
            recoil = 3f;
            range = 135f;
            rotateSpeed = 8f;
            shootEffect = Fx.shootBigColor;
            shootSound = Sounds.shootBig;
            targetGround = false;
            outlineColor = DustedPal.darkerWarmMetal;
            drawer = new DrawTurret("decayed-");

            shoot = new ShootSpread(5, 8f);

            ammo(
                    DustedPowders.orchar, new BasicBulletType(4.5f, 10f, "circle-bullet") {{
                        collidesGround = false;
                        width = height = 14f;
                        shrinkX = shrinkY = 0.4f;
                        splashDamageRadius = 20f;
                        splashDamage = 24f;
                        status = DustedStatusEffects.blazing;
                        statusDuration = 4f * 60f;
                        frontColor = DustedPal.lightOrchar;
                        backColor = hitColor = trailColor = DustedPal.darkOrchar;
                        trailChance = 0.4f;
                        trailEffect = Fx.hitSquaresColor;
                        shootEffect = DustedFx.shootPowder;
                        hitEffect = DustedFx.hitPowder;
                        lifetime = 30f;
                    }}
            );

            coolant = consumeCoolant(0.1f);
            researchCost = ItemStack.with(DustedItems.zircon, 30, DustedItems.arsenic, 20, DustedItems.antimony, 20);
        }};

        sunder = new PowderTurret("sunder") {{
            requirements(Category.turret, ItemStack.with(DustedItems.zircon, 80, DustedItems.antimony, 60, Items.silicon, 50));
            size = 2;
            scaledHealth = 220f;
            reload = 80f;
            range = 135f;
            inaccuracy = 5f;
            shootSound = Sounds.missile;

            shoot.shots = 2;

            outlineColor = DustedPal.darkerWarmMetal;
            drawer = new DrawTurret("decayed-");

            ammo(
                    DustedPowders.orchar, new BasicBulletType(4.5f, 18f) {{
                        lifetime = 30f;
                        width = 18f;
                        height = 22f;
                        frontColor = DustedPal.lightOrchar;
                        backColor = hitColor = trailColor = DustedPal.darkOrchar;
                        trailLength = 14;
                        trailWidth = 6f;
                        hitEffect = despawnEffect = Fx.hitSquaresColor;
                        shootEffect = Fx.shootSmallColor;
                        smokeEffect = Fx.shootSmokeSquare;
                        status = DustedStatusEffects.blazing;
                        statusDuration = 6 * 60f;
                        hitSound = Sounds.bang;
                    }}
            );

            researchCost = ItemStack.with(DustedItems.zircon, 100, DustedItems.arsenic, 50, DustedItems.antimony, 50, Items.silicon, 50);
        }};

        //TODO redo this entirely maybe
        bisect = new PowerTurret("bisect") {{
            requirements(Category.turret, BuildVisibility.hidden, ItemStack.with());
            size = 2;
            scaledHealth = 220f;
            targetAir = false;
            reload = 70f;
            recoil = 1f;
            range = 100f;
            shootSound = Sounds.laser;

            outlineColor = DustedPal.darkerWarmMetal;
            drawer = new DrawTurret("decayed-");

            shootType = new SplittingLaserBulletType(90f) {{
                hitSize = 8f;
                lifetime = 40f;
                drawSize = 100f;
                shootEffect = DustedFx.splitShot;
                collidesAir = false;
                length = 100f;
                ammoMultiplier = 1f;
            }};

            consumePower(2f);
            coolant = consumeCoolant(0.1f);
        }};

        coruscate = new PowderTurret("coruscate") {{
            requirements(Category.turret, ItemStack.with(DustedItems.arsenic, 110, DustedItems.antimony, 80, Items.silicon, 70));
            size = 3;
            scaledHealth = 280f;
            reload = 110f;
            recoil = 5f;
            shootSound = Sounds.artillery;
            range = 3f * 70f;
            outlineColor = DustedPal.darkerWarmMetal;
            shootWarmupSpeed = 0.15f;
            cooldownTime = 140f;

            drawer = new DrawTurret("decayed-") {{
                parts.add(
                        new RegionPart("-blade") {{
                            progress = PartProgress.recoil;
                            mirror = true;
                            under = true;
                            x = 3.5f;
                            y = 4.75f;
                            moveX = 2f;
                            moveY = -1f;
                            moveRot = -20f;
                        }},
                        new RegionPart("-blade-glow") {{
                            progress = PartProgress.recoil;
                            mirror = true;
                            under = true;
                            heatProgress = PartProgress.warmup;
                            heatColor = Color.valueOf("ff3b62");
                            drawRegion = false;
                            x = 3.5f;
                            y = 4.75f;
                            moveX = 2f;
                            moveY = -1f;
                            moveRot = -20f;
                        }},
                        new RegionPart("-glow") {{
                            heatProgress = PartProgress.warmup;
                            heatColor = Color.valueOf("ff3b62");
                            drawRegion = false;
                        }}
                );
            }};

            ammo(
                    DustedPowders.quartzDust, new RocketBulletType(3f, 36f) {{
                        width = height = 18f;
                        splashDamage = 35f;
                        splashDamageRadius = 22f;
                        lifetime = 70f;
                        frontColor = DustedPal.lightQuartz;
                        backColor = hitColor = trailColor = DustedPal.darkQuartz;
                        trailLength = 16;
                        trailWidth = 4f;
                        status = StatusEffects.burning;
                        shootEffect = DustedFx.shootPowder;
                        statusDuration = 16 * 60f;
                        hitEffect = despawnEffect = DustedFx.hitPowder;

                        shoot = new ShootSpread(3, 30f);

                        shootSound = Sounds.explosion;

                        rocketBulletType = new BasicBulletType(1.6f, 16f) {{
                            width = height = 12f;
                            splashDamage = 12f;
                            splashDamageRadius = 18f;
                            lifetime = 30f;
                            frontColor = DustedPal.lightQuartz;
                            backColor = hitColor = DustedPal.darkQuartz;
                            shootEffect = DustedFx.shootPowder;
                            hitEffect = despawnEffect = DustedFx.hitPowder;
                            makeFire = true;
                            status = StatusEffects.burning;
                            statusDuration = 3 * 60f;
                        }};
                    }}
            );

            coolant = consumeCoolant(0.1f);
        }};

        strike = new ItemPowderTurret("strike") {{
            requirements(Category.turret, ItemStack.with(DustedItems.arsenic, 120, DustedItems.antimony, 100, Items.silicon, 70));
            size = 3;
            scaledHealth = 240f;
            outlineColor = DustedPal.darkerWarmMetal;
            rotateSpeed = 1f;
            reload = 6f;
            recoilTime = 30f;
            cooldownTime = 40f;
            recoil = 2f;
            shootSound = Sounds.bang;
            shootEffect = DustedFx.shootLaunch;
            shootWarmupSpeed = 0.05f;
            minWarmup = 0.8f;
            range = 240f;
            shootY = 10f;

            drawer = new DrawTurret("decayed-") {{
                parts.add(
                        new RegionPart("-blade") {{
                            progress = PartProgress.warmup;
                            mirror = true;
                            under = true;
                            moveX = 7f;
                            moveRot = -40f;
                            moves.add(new PartMove(PartProgress.warmup, 5f, 2f, -5f));
                        }},
                        new RegionPart("-blade") {{
                            progress = PartProgress.warmup;
                            mirror = true;
                            under = true;
                            moveX = 6f;
                            moveY = -4f;
                            moveRot = -60f;
                            moves.add(new PartMove(PartProgress.warmup, 4f, -2f, -4f));
                        }},
                        new RegionPart("-glow") {{
                            heatProgress = PartProgress.recoil;
                            heatColor = Color.valueOf("1013e0");
                            drawRegion = false;
                        }}
                );
            }};

            ammo(
                    //TODO this is probably unbalanced
                    DustedItems.antimony, new InstantBulletType() {{
                        damage = 18f;
                        splashDamage = 10f;
                        splashDamageRadius = 4f;
                        status = StatusEffects.blasted;
                        hitEffect = despawnEffect = DustedFx.hitLaunch;
                    }}
            );

            consume(new ConsumePowder(DustedPowders.sulfur, 0.2f));
        }};

        blight = new PowderTurret("blight") {{
            requirements(Category.turret, ItemStack.with(DustedItems.arsenic, 240, DustedItems.antimony, 160, Items.silicon, 120, DustedItems.platinum, 40));
            size = 4;
            reload = 190f;
            scaledHealth = 260f;
            range = 340f;
            shootY = 6f;
            //TODO maybe change to a more firework-like sound?
            shootSound = Sounds.artillery;
            outlineColor = DustedPal.darkerWarmMetal;
            drawer = new DrawTurret("decayed-") {{
                parts.addAll(
                        new RegionPart("-blade") {{
                            mirror = true;
                            under = true;
                            x = 8f;
                            y = 3f;
                            moveX = 7f;
                            moveY = 4f;
                            moveRot = 50f;
                            moves.add(new PartMove(PartProgress.recoil, 0f, -1f, -40f));
                            heatProgress = PartProgress.warmup;
                            heatColor = Color.valueOf("ff3b62");
                        }},
                        new RegionPart("-blade") {{
                            mirror = true;
                            under = true;
                            x = 8f;
                            y = 3f;
                            moveX = 9f;
                            moveY = -2f;
                            moveRot = 20f;
                            moves.add(new PartMove(PartProgress.recoil, 0f, -1f, -40f));
                            heatProgress = PartProgress.warmup;
                            heatColor = Color.valueOf("ff3b62");
                        }},
                        new RegionPart("-blade") {{
                            mirror = true;
                            under = true;
                            x = 8f;
                            y = 3f;
                            moveX = 6f;
                            moveY = -7f;
                            moveRot = -30f;
                            moves.add(new PartMove(PartProgress.recoil, 0f, -1f, -40f));
                            heatProgress = PartProgress.warmup;
                            heatColor = Color.valueOf("ff3b62");
                        }},
                        new RegionPart("-side") {{
                            x = 7.25f;
                            y = -1f;
                            mirror = true;
                            under = true;
                            moveX = 0.5f;
                            moveY = 2.5f;
                            heatProgress = PartProgress.recoil;
                            heatColor = Color.valueOf("ff3b62");
                        }},
                        new RegionPart("-barrel") {{
                            under = true;
                            progress = PartProgress.recoil;
                            moveY = -3f;
                            heatProgress = PartProgress.recoil;
                            heatColor = Color.valueOf("ff3b62");
                        }}
                );
            }};

            ammo(
                    DustedPowders.quartzDust, new RocketBulletType(7f, 45f, "circle-bullet") {
                        {
                            lifetime = 240f;
                            drag = 0.02f;
                            width = height = 18f;
                            shrinkX = shrinkY = 0.25f;
                            ammoMultiplier = 1f;
                            pierce = true;
                            frontColor = DustedPal.lightQuartz;
                            backColor = trailColor = hitColor = DustedPal.darkQuartz;
                            shootEffect = DustedFx.shootPowderSquares;
                            hitEffect = despawnEffect = DustedFx.hitPowder;
                            trailWidth = 9f;
                            trailInterp = i -> 1f - shrinkX * i;
                            trailLength = 22;
                            status = StatusEffects.burning;
                            statusDuration = 6 * 60f;

                            rocketReload = 5f;
                            rocketDelay = 50f;
                            inaccuracy = 180f;
                            shootSound = Sounds.missile;

                            rocketBulletType = new BasicBulletType(3f, 30f) {{
                                width = 12f;
                                height = 18f;
                                lifetime = 40f;
                                frontColor = DustedPal.lightQuartz;
                                backColor = trailColor = hitColor = DustedPal.darkQuartz;
                                shootEffect = DustedFx.shootPowder;
                                hitEffect = despawnEffect = DustedFx.hitPowder;
                                trailWidth = 3f;
                                trailLength = 8;
                            }};
                        }

                        @Override
                        public void removed(Bullet b) {
                            //doesn't do anything, only overriden so that it doesn't draw trail fade
                        }
                    }
            );
        }};
        //endregion
        //region units
        cavnenTerraConstructor = new UnitFactory("cavnen-terra-constructor") {{
            requirements(Category.units, ItemStack.with(Items.silicon, 40, DustedItems.zircon, 80, DustedItems.antimony, 50));
            plans = Seq.with(
                    new UnitPlan(DustedUnitTypes.pique, 60f * 15f, ItemStack.with(Items.silicon, 15, DustedItems.arsenic, 10))
            );
            size = 3;
            consumePower(1f);
        }};

        cavnenAerialConstructor = new UnitFactory("cavnen-aerial-constructor") {{
            requirements(Category.units, ItemStack.with(DustedItems.zircon, 60, DustedItems.antimony, 50));
            plans = Seq.with(
                    new UnitPlan(DustedUnitTypes.carom, 60f * 15f, ItemStack.with(Items.silicon, 10, DustedItems.arsenic, 10)),
                    new UnitPlan(DustedUnitTypes.sob, 60f * 30f, ItemStack.with(Items.silicon, 20, DustedItems.zircon, 30, DustedItems.antimony, 20))
            );
            size = 3;
            consumePower(1f);
        }};

        binaryRestructurer = new PowderReconstructor("binary-restructurer") {{
            requirements(Category.units, ItemStack.with(DustedItems.zircon, 120, Items.metaglass, 90, Items.silicon, 75));
            size = 3;
            constructTime = 10 * 60f;

            consumePower(4f);
            consume(new ConsumePowder(DustedPowders.orchar, 0.2f));
            consumeItems(ItemStack.with(Items.silicon, 50, DustedItems.arsenic, 40));

            upgrades.addAll(
                    new UnitType[]{DustedUnitTypes.carom , DustedUnitTypes.recur},
                    new UnitType[]{DustedUnitTypes.pique, DustedUnitTypes.rancor}
            );
        }};

        ternaryRestructurer = new PowderReconstructor("ternary-restructurer") {{
            requirements(Category.units, BuildVisibility.hidden, ItemStack.with());
            size = 5;
            constructTime = 25 * 60f;

            consumePower(10f);
            consume(new ConsumePowder(DustedPowders.orchar, 0.5f));
            consumeItems(ItemStack.with(Items.silicon, 200, DustedItems.antimony, 150, Items.metaglass, 100));
            upgrades.addAll(
                    new UnitType[]{DustedUnitTypes.recur, DustedUnitTypes.saltate},
                    new UnitType[]{DustedUnitTypes.rancor, DustedUnitTypes.animus}
            );
            /*
        quarternaryRestructurer = new PowderReconstructor("ternary-restructurer") {{
            requirements(Category.units, BuildVisibility.hidden, ItemStack.with());
            size = 7;
            constructTime = 50 * 60f;

            consumePower(16f);
            consume(new ConsumePowder(DustedPowders.sulfur, 0.6f));
            consumeItems(ItemStack.with(Items.silicon, 400, Items.metaglass, 200, DustedItems.platinum, 300));
            upgrades.addAll(
                    new UnitType[]{DustedUnitTypes.saltate, DustedUnitTypes.staccato},
                    new UnitType[]{DustedUnitTypes.animus, tmpNothing}
            );
            
        quinaryRestructurer = new PowderReconstructor("ternary-restructurer") {{
            requirements(Category.units, BuildVisibility.hidden, ItemStack.with());
            size = 9;
            constructTime = 120 * 60f;

            consumePower(30f);
            consume(new ConsumePowder(DustedPowders.sulfur, 0.8f));
            consumeItems(ItemStack.with(Items.silicon, 1200, DustedItems.rockwool, 800, DustedItems.platinum, 600, DustedItems.telonate, 400));
            upgrades.addAll(
                    new UnitType[]{DustedUnitTypes.staccato, tmpNothing},
                    new UnitType[]{tmpNothing, tmpNothing}
            );*/
        }};
        //endregion
        //region cores
        coreAbate = new ShieldedCoreBlock("core-abate") {{
            requirements(Category.effect, ItemStack.with(DustedItems.zircon, 1200, DustedItems.arsenic, 1000));
            unitType = DustedUnitTypes.erode;
            squareSprite = false;
            size = 3;
            health = 2000;
            itemCapacity = 2000;
            alwaysUnlocked = true;
            isFirstTier = true;
        }};

        coreDissent = new ShieldedCoreBlock("core-dissent") {{
            requirements(Category.effect, ItemStack.with(DustedItems.zircon, 4000, DustedItems.arsenic, 3200, Items.silicon, 3000));
            unitType = DustedUnitTypes.recede;
            squareSprite = false;
            size = 4;
            radius = 160f;
            health = 4500;
            itemCapacity = 4000;
        }};

        coreDecadence = new ShieldedCoreBlock("core-decadence") {{
            requirements(Category.effect, BuildVisibility.hidden, ItemStack.with());
            unitType = DustedUnitTypes.atrophy;
            radius = 220f;
        }};

        //endregion
        //region sandbox
        powderSource = new PowderSource("powder-source") {{
            requirements(Category.distribution, BuildVisibility.sandboxOnly, ItemStack.with());
        }};

        powderVoid = new PowderVoid("powder-void") {{
            requirements(Category.distribution, BuildVisibility.sandboxOnly, ItemStack.with());
        }};

        cocaineDuo = new PowderTurret("cocaine-duo") {{
            requirements(Category.turret, BuildVisibility.sandboxOnly, ItemStack.with());
            ammo(
                    DustedPowders.cocaine, new BasicBulletType(2.5f, 1000000f) {{
                        frontColor = Color.white;
                        backColor = Color.lightGray;
                        width = 7f;
                        height = 9f;
                        lifetime = 44.4f;
                        shootEffect = Fx.shootSmall;
                        smokeEffect = Fx.shootSmallSmoke;
                        ammoMultiplier = 2;
                    }}
            );

            shoot = new ShootAlternate(3.5f);

            shootY = 3f;
            reload = 20f;
            range = 110;
            shootCone = 15f;
            ammoUseEffect = Fx.casing1;
            health = 250;
            inaccuracy = 2f;
            rotateSpeed = 10f;
            coolant = consumeCoolant(0.1f);
        }};
        //endregion
    }
}
