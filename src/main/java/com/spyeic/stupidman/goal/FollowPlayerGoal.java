package com.spyeic.stupidman.goal;

import com.spyeic.stupidman.entity.mob.StupidManEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

import java.util.EnumSet;

public class FollowPlayerGoal extends Goal {

    public static final TargetPredicate CLOSE_PLAYER_PREDICATE = new TargetPredicate().setBaseMaxDistance(10.0).includeTeammates().includeInvulnerable().includeHidden();
    private final StupidManEntity stupidMan;
    private final double speed;
    private PlayerEntity closestPlayer;
    private int ticks = 0;

    public FollowPlayerGoal(StupidManEntity mobEntity, double d) {
        this.stupidMan = mobEntity;
        this.speed = d;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        this.closestPlayer = this.stupidMan.world.getClosestPlayer(CLOSE_PLAYER_PREDICATE, this.stupidMan);
        if (this.closestPlayer == null) {
            return false;
        }
        return this.stupidMan.getTarget() != this.closestPlayer;
    }

    @Override
    public boolean shouldContinue() {
        return this.closestPlayer != null && this.stupidMan.squaredDistanceTo(this.closestPlayer) < 256.0;
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
        this.closestPlayer = null;
        this.stupidMan.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.stupidMan.getLookControl().lookAt(this.closestPlayer, this.stupidMan.getBodyYawSpeed() + 20, this.stupidMan.getLookPitchSpeed());
        if (this.stupidMan.squaredDistanceTo(this.closestPlayer) < 3.25) {
            this.stupidMan.getNavigation().stop();
            ticks++;
            if (ticks >= 15) {
                PlayerInventory inventory = this.closestPlayer.inventory;
                if (!inventory.main.get(inventory.selectedSlot).isEmpty()) {
                    ItemStack itemStack = inventory.main.get(inventory.selectedSlot);
                    if (itemStack.getCount() > 1) {
                        itemStack.decrement(1);
                    } else {
                        inventory.main.set(inventory.selectedSlot, ItemStack.EMPTY);
                    }
                } else if (!inventory.offHand.get(0).isEmpty()) {
                    ItemStack itemStack = inventory.offHand.get(0);
                    if (itemStack.getCount() > 1) {
                        itemStack.decrement(1);
                    } else {
                        inventory.main.set(inventory.selectedSlot, ItemStack.EMPTY);
                    }
                } else {
                    for (int i = 0; i < inventory.main.size(); i++) {
                        if (!inventory.main.get(i).isEmpty()) {
                            ItemStack itemStack = inventory.main.get(i);
                            if (itemStack.getCount() > 1) {
                                itemStack.decrement(1);
                            } else {
                                inventory.main.set(inventory.selectedSlot, ItemStack.EMPTY);
                            }
                            break;
                        }
                    }
                }
                ticks = 0;
            }
        } else {
            this.stupidMan.getNavigation().startMovingTo(this.closestPlayer, this.speed);
            ticks = 0;
        }
    }
}
