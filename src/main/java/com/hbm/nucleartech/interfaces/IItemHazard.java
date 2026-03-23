package com.hbm.nucleartech.interfaces;

import com.hbm.nucleartech.modules.ItemHazardModule;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface IItemHazard {

    public ItemHazardModule getModule();
    // --------- Getters ---------
    public default double getRadiationLevel() {
        return this.getModule().getRadiationLevel();
    }
    public default double getDigammaLevel() {
        return this.getModule().getDigammaLevel();
    }
    public default int getFireLevel() {
        return this.getModule().getFireLevel();
    }
    public default int getCryogenicLevel() {
        return this.getModule().getCryogenicLevel();
    }
    public default int getToxicLevel() {
        return this.getModule().getToxicLevel();
    }
    public default double getAsbestosLevel() {
        return this.getModule().getAsbestosLevel();
    }
    public default int getCoalLevel() {
        return this.getModule().getCoalLevel();
    }
    // --------- Adders ---------
    public default IItemHazard addRadiation(double radiation) {

        this.getModule().addRadiation(radiation);
        return this;
    }

    public default IItemHazard addDigamma(double digamma) {

        this.getModule().addDigamma(digamma);
        return this;
    }

    public default IItemHazard addFire(int fire) {

        this.getModule().addFire(fire);
        return this;
    }

    public default IItemHazard addCryogenic(int cryo) {

        this.getModule().addCryogenic(cryo);
        return this;
    }

    public default IItemHazard addToxic(int toxiclvl) {

        this.getModule().addToxic(toxiclvl);
        return this;
    }

    public default IItemHazard addAsbestos(double asbestos) {

        this.getModule().addAsbestos(asbestos);
        return this;
    }

    public default IItemHazard addCoal(int coal) {

        this.getModule().addCoal(coal);
        return this;
    }

    public default IItemHazard addBlinding() {

        this.getModule().addBlinding();
        return this;
    }

    public default IItemHazard addHydroReactivity() {

        this.getModule().addHydroReactivity();
        return this;
    }

    public default IItemHazard addExplosive(float bang) {

        this.getModule().addExplosive(bang);
        return this;
    }
    // --------- Checkers ---------
    public default boolean isRadioactive() {

        return this.getModule().isRadioactive();
    }
    public default boolean isBlinding() {

        return this.getModule().isBlinding();
    }
    public default boolean isHydroReactive() {

        return this.getModule().isHydroReactive();
    }
    public default boolean isExplosive() {

        return this.getModule().isExplosive();
    }
    public default boolean isToxic() {

        return this.getModule().isToxic();
    }
    public default boolean isFireHazard() {

        return this.getModule().isFireHazard();
    }
    public default boolean isCryogenic() {

        return this.getModule().isCryogenic();
    }
    public default boolean isDigamma() {

        return this.getModule().isDigamma();
    }
    public default boolean isCoal() {

        return this.getModule().isCoal();
    }
    public default boolean isAsbestos() {

        return this.getModule().isAsbestos();
    }
    public default boolean isHazardous() {

        return this.getModule().isHazardous();
    }
    // --------- Converters ---------

    public default Item toItem() {

        return (Item)this;
    }

    public default Block toBlock() {

        return (Block)this;
    }
}
