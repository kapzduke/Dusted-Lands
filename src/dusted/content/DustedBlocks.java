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
import dusted.world.blocks.production.*;
import dusted.world.blocks.storage.*;
import dusted.world.blocks.units.*;
import dusted.world.consumers.*;
import dusted.world.draw.*;
import dusted.world.meta.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.units.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

//TODO balancing, campaign stuff
public class DustedBlocks {
    public static Block
    //environment, TODO resprite this
    oreZircon, oreArsenic, oreAntimony, pyreol, sulfur, volcanoZone,
    cavnenSediment, cavnenDusting, volstone, volTerracotta, latite, scoria, stradrock, scorchedStradrock,
    cavnenWall, volstoneWall, scoriaWall, latiteWall, stradrockWall,
    //decor
    scoriaBoulder, latiteBoulder, stradrockBoulder,
    volstoneBoulder, cavnenBoulder, volSprout, volTree, weepingShrub, weepingBlossom, charredTree,
    //defense
    zirconWall, zirconWallLarge,
    decaySuppressor,
    //turrets, TODO balancing?
    abrade, sunder, bisect, scald, coruscate, strike, blight,
    cocaineDuo,
    //distribution
    transferLink, transferTower,
    //powder distribution
    chute, powderRouter, powderJunction, bridgeChute,
    denseChute, armoredChute,
    //power TODO rework this as well
    powerElectrode,
    //crafters
    quartzExtractor, metaglassFurnace, siliconForge, rockwoolExtruder, pyresinCondenser,
    //production
    pneumaticFunnel, rotaryFunnel,
    //cores
    coreAbate, coreDissent, coreDecadence,
    //units
    //TODO maybe use a different system
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

        pyreol = new PowderFloor("pyreol-deposit") {{
            powderDrop = DustedPowders.pyreol;
            attributes.set(Attribute.water, -0.7f);
        }};

        sulfur = new PowderFloor("sulfur-deposit") {{
            powderDrop = DustedPowders.sulfur;
            attributes.set(Attribute.water, -0.5f);
        }};

        cavnenSediment = new Floor("cavnen-sediment") {{
            attributes.set(Attribute.oil, 1.2f);
            attributes.set(Attribute.water, -0.6f);
        }};

        cavnenDusting = new PowderFloor("cavnen-dusting") {{
            attributes.set(Attribute.oil, 0.9f);
            attributes.set(Attribute.water, -0.65f);
        }};

        volstone = new Floor("volstone");

        volTerracotta = new Floor("vol-terracotta");

        scoria = new Floor("scoria");

        latite = new Floor("latite");

        stradrock = new Floor("stradrock");

        scorchedStradrock = new Floor("scorched-stradrock");

        cavnenWall = new StaticWall("cavnen-wall") {{
            cavnenSediment.asFloor().wall = this;
            cavnenDusting.asFloor().wall = this;
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

        charredTree = new TreeBlock("charred-tree");

        volSprout = new Prop("vol-sprout") {{
            variants = 2;
        }};

        cavnenBoulder = new Prop("cavnen-boulder") {{
            variants = 2;
            cavnenSediment.asFloor().decoration = cavnenDusting.asFloor().decoration = this;
        }};

        volstoneBoulder = new Prop("volstone-boulder") {{
            variants = 2;
            volstone.asFloor().decoration = this;
        }};

        //endregion
        //region crafters
        quartzExtractor = new PowderCrafter("quartz-extractor") {{
            requirements(Category.crafting, ItemStack.with(Items.titanium, 100, DustedItems.zircon, 60, Items.copper, 50));
            hasPower = true;
            size = 2;

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPowder(DustedPowders.quartzDust), new DrawDefault());
            outputPowder = new PowderStack(DustedPowders.quartzDust, 1f);
            craftTime = 12f;
            consumePower(1f);
            consumeItem(Items.sand, 3);
        }};

        siliconForge = new PowderCrafter("silicon-forge") {{
            requirements(Category.crafting, ItemStack.with(Items.titanium, 130, DustedItems.zircon, 80, Items.graphite, 50));
            size = 3;
            squareSprite = false;

            outputItem = new ItemStack(Items.silicon, 3);
            craftTime = 90f;
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawSpinFlame());
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.08f;

            consumePower(0.6f);
            consume(new ConsumePowder(DustedPowders.quartzDust, 0.05f));
            consumeItem(Items.graphite, 2);
        }};

        metaglassFurnace = new PowderCrafter("metaglass-furnace") {{
            requirements(Category.crafting, ItemStack.with());
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
            consumeItem(DustedItems.platinum, 2);
        }};

        rockwoolExtruder = new DrawerWallCrafter("rockwool-extruder") {{
            requirements(Category.crafting, ItemStack.with());
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

        pyresinCondenser = new PowderCrafter("pyresin-condenser") {{
            requirements(Category.crafting, ItemStack.with());
            hasPower = true;
            size = 3;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawPowder(DustedPowders.pyreol, 2f),
                    new DrawRegion("-rotator") {{
                        spinSprite = true;
                        rotateSpeed = -2f;
                    }},
                    new DrawDefault(),
                    new DrawRegion("-top")
            );
            outputItem = new ItemStack(DustedItems.pyresin, 2);
            craftTime = 50f;
            consumePower(1.5f);
            consume(new ConsumePowder(DustedPowders.pyreol, 0.1f));
        }};
        //endregion
        //region distribution
        transferLink = new TransferLink("transfer-link") {{
            requirements(Category.distribution, ItemStack.with(DustedItems.zircon, 3));
            squareSprite = false;
        }};

        transferTower = new TransferLink("transfer-tower") {{
            requirements(Category.distribution, ItemStack.with(DustedItems.zircon, 10, Items.graphite, 7, Items.silicon, 5));
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
            requirements(Category.distribution, ItemStack.with(DustedItems.zircon, 1, DustedItems.arsenic, 2, Items.graphite, 2));
            powderPressure = 1.03f;
            powderCapacity = 16f;
            health = 100;
        }};

        armoredChute = new ArmoredChute("armored-chute") {{
            requirements(Category.distribution, ItemStack.with(DustedItems.arsenic, 2, DustedItems.pyresin, 3));
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
        pneumaticFunnel = new Funnel("pneumatic-funnel") {{
            requirements(Category.production, ItemStack.with(DustedItems.zircon, 10));
            funnelAmount = 7f / 60f;
            powderCapacity = 20f;
            squareSprite = false;
        }};

        rotaryFunnel = new Funnel("rotary-funnel") {{
            requirements(Category.production, ItemStack.with(Items.graphite, 40, DustedItems.zircon, 20));
            size = 2;
            hasPower = true;
            powderCapacity = 40f;
            consumePower(0.4f);
            extractEffect = DustedFx.funnelExtract;
            squareSprite = false;
        }};
        //endregion
        //region power
        powerElectrode = new PowerNode("power-electrode") {{
            requirements(Category.power, ItemStack.with(Items.graphite, 3, DustedItems.zircon, 1));
            maxNodes = 5;
            laserRange = 3.5f;
        }};
        //endregion
        //region defense
        zirconWall = new Wall("zircon-wall") {{
            requirements(Category.defense, ItemStack.with(DustedItems.zircon, 6));
            health = 280;
        }};

        zirconWallLarge = new Wall("zircon-wall-large") {{
            requirements(Category.defense, ItemStack.mult(zirconWall.requirements, 4));
            health = 280 * 4;
            size = 2;
        }};

        decaySuppressor = new DecaySuppressor("decay-suppressor") {{
           requirements(Category.effect, ItemStack.with());
           size = 3;
           consumePower(1f);
        }};
        //endregion
        //region turrets
        abrade = new ItemTurret("abrade") {{
            requirements(Category.turret, ItemStack.with());
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
                        shootEffect = DustedFx.shootCavnenShrapnel;
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
        }};

        sunder = new ItemTurret("sunder") {{
            requirements(Category.turret, ItemStack.with());
            size = 2;
            scaledHealth = 220f;
            reload = 80f;
            range = 280f;

            outlineColor = DustedPal.darkerWarmMetal;
            drawer = new DrawTurret("decayed-");

            ammo(
                    DustedItems.arsenic, new RocketBulletType(3.5f, 18f) {{
                        lifetime = 80f;
                        width = 18f;
                        height = 22f;
                        splashDamage = 12f;
                        splashDamageRadius = 16f;
                        frontColor = Color.white;
                        backColor = hitColor = Color.valueOf("ff708e");
                        hitEffect = despawnEffect = Fx.hitSquaresColor;

                        rocketBulletType = new BasicBulletType(2f, 12f, "mine-bullet") {{
                            lifetime = 65f;
                            width = height = 12f;
                            shrinkX = shrinkY = 0.3f;
                            drag = 0.06f;
                            splashDamage = 20f;
                            splashDamageRadius = 24f;
                            frontColor = Color.white;
                            backColor = hitColor = Color.valueOf("ff708e");
                            shootEffect = Fx.hitBulletColor;
                            hitEffect = despawnEffect = Fx.hitSquaresColor;
                        }};
                    }}
            );
        }};

        //TODO redo this entirely maybe
        bisect = new PowerTurret("bisect") {{
            requirements(Category.turret, ItemStack.with());
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

            coolant = consumeCoolant(0.1f);
        }};

        scald = new PowderTurret("scald") {{
            requirements(Category.turret, ItemStack.with());
            size = 2;
            scaledHealth = 260f;
            reload = 80f;
            recoil = 3f;
            range = 100f;
            shootEffect = Fx.shootBig2;
            shootSound = Sounds.shootBig;
            targetAir = false;
            outlineColor = DustedPal.darkerWarmMetal;
            drawer = new DrawTurret("decayed-");

            ammo(
                    DustedPowders.quartzDust, new BasicBulletType(3.5f, 30f) {{
                        collidesAir = false;
                        width = height = 15f;
                        splashDamageRadius = 20f;
                        splashDamage = 24f;
                        status = StatusEffects.burning;
                        statusDuration = 8 * 60f;
                        frontColor = DustedPal.lightQuartz;
                        backColor = hitColor = DustedPal.darkQuartz;
                        shootEffect = DustedFx.shootPowder;
                        hitEffect = DustedFx.hitPowder;
                        lifetime = 30f;
                        makeFire = true;

                        fragBullets = 3;
                        fragBullet = new ArtilleryBulletType(3f, 20f) {{
                            width = height = 12f;
                            lifetime = 25f;
                            splashDamageRadius = 18f;
                            splashDamage = 16f;
                            makeFire = true;
                            hitEffect = DustedFx.hitPowder;
                            frontColor = DustedPal.lightQuartz;
                            backColor = hitColor = DustedPal.darkQuartz;
                            status = StatusEffects.burning;
                            statusDuration = 6 * 60f;
                        }};
                    }}
            );

            coolant = consumeCoolant(0.1f);
        }};

        //TODO change weapon maybe
        coruscate = new PowderTurret("coruscate") {{
            requirements(Category.turret, ItemStack.with());
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
            requirements(Category.turret, ItemStack.with());
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
                    Items.graphite, new InstantBulletType() {{
                        damage = 18f;
                        splashDamage = 10f;
                        splashDamageRadius = 4f;
                        status = StatusEffects.blasted;
                        statusDuration = 4 * 60f;
                        hitEffect = despawnEffect = DustedFx.hitLaunch;
                    }}
            );

            consume(new ConsumePowder(DustedPowders.sulfur, 0.2f));
        }};

        blight = new PowderTurret("blight") {{
            requirements(Category.turret, ItemStack.with());
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
                    DustedPowders.pyreol, new RocketBulletType(7f, 45f, "circle-bullet") {
                        {
                            lifetime = 240f;
                            drag = 0.02f;
                            width = height = 18f;
                            shrinkX = shrinkY = 0.25f;
                            ammoMultiplier = 1f;
                            pierce = true;
                            frontColor = DustedPal.lightPyreol;
                            backColor = trailColor = hitColor = DustedPal.darkPyreol;
                            shootEffect = DustedFx.shootPowderSquares;
                            hitEffect = despawnEffect = DustedFx.hitPowder;
                            trailWidth = 9f;
                            trailInterp = i -> 1f - shrinkX * i;
                            trailLength = 22;
                            status = DustedStatusEffects.blazing;
                            statusDuration = 6 * 60f;

                            rocketReload = 5f;
                            rocketDelay = 50f;
                            inaccuracy = 180f;
                            shootSound = Sounds.missile;

                            rocketBulletType = new BasicBulletType(3f, 30f) {{
                                width = 12f;
                                height = 18f;
                                lifetime = 40f;
                                frontColor = DustedPal.lightPyreol;
                                backColor = trailColor = hitColor = DustedPal.darkPyreol;
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
            requirements(Category.units, ItemStack.with(Items.copper, 80, DustedItems.zircon, 60, Items.titanium, 50));
            plans = Seq.with(
                    new UnitPlan(DustedUnitTypes.pique, 60f * 15f, ItemStack.with(Items.silicon, 15, DustedItems.arsenic, 10))
            );
            size = 3;
            consumePower(1f);
        }};

        cavnenAerialConstructor = new UnitFactory("cavnen-aerial-constructor") {{
            requirements(Category.units, ItemStack.with(DustedItems.zircon, 60, Items.titanium, 50));
            plans = Seq.with(
                    new UnitPlan(DustedUnitTypes.carom, 60f * 10f, ItemStack.with(Items.silicon, 10, Items.titanium, 10))
            );
            size = 3;
            consumePower(1f);
        }};

        binaryRestructurer = new PowderReconstructor("binary-restructurer") {{
            requirements(Category.units, ItemStack.with(Items.copper, 120, Items.titanium, 90, DustedItems.zircon, 75, Items.thorium, 50));
            size = 3;
            constructTime = 10 * 60f;

            consumePower(2f);
            consumeItems(ItemStack.with(Items.silicon, 50, DustedItems.arsenic, 40));

            upgrades.addAll(
                    new UnitType[]{DustedUnitTypes.carom, DustedUnitTypes.recur},
                    new UnitType[]{DustedUnitTypes.pique, DustedUnitTypes.rancor}
            );
        }};

        ternaryRestructurer = new PowderReconstructor("ternary-restructurer") {{
            requirements(Category.units, ItemStack.with());
            size = 5;

            consumePower(5f);
            consumeItems(ItemStack.with(Items.silicon, 120, Items.titanium, 100, DustedItems.zircon, 60));
            upgrades.addAll(
                    new UnitType[]{DustedUnitTypes.recur, DustedUnitTypes.saltate},
                    new UnitType[]{DustedUnitTypes.rancor, DustedUnitTypes.animus}
            );
        }};
        //endregion
        //region cores
        coreAbate = new ShieldedCoreBlock("core-abate") {{
            requirements(Category.effect, BuildVisibility.editorOnly, ItemStack.with());
            unitType = DustedUnitTypes.erode;
            squareSprite = false;
            size = 3;
            health = 2500;
            itemCapacity = 2000;
        }};

        coreDissent = new ShieldedCoreBlock("core-dissent") {{
            requirements(Category.effect, ItemStack.with());
            unitType = DustedUnitTypes.recede;
            size = 4;
            radius = 160f;
        }};

        coreDecadence = new ShieldedCoreBlock("core-decadence") {{
            requirements(Category.effect, ItemStack.with());
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