package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.capabilities.CapabilityConsciousness;
import com.gonggongjohn.eok.capabilities.CapabilityMindActivity;
import com.gonggongjohn.eok.capabilities.IConsciousness;
import com.gonggongjohn.eok.capabilities.IMindActivity;
import com.gonggongjohn.eok.network.PacketConsciousness;
import com.gonggongjohn.eok.network.PacketMindActivity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AnotherEventHandler {
    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof EntityPlayer){
            ICapabilitySerializable<NBTTagCompound> providerConsciousness = new CapabilityConsciousness.ProvidePlayer();
            ICapabilitySerializable<NBTTagCompound> providerMindActivity = new CapabilityMindActivity.ProvidePlayer();
            event.addCapability(new ResourceLocation(EOK.MODID + ":" + "consciousness"), providerConsciousness);
            event.addCapability(new ResourceLocation(EOK.MODID + ":" + "mindActivity"), providerMindActivity);
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){
        Capability<IConsciousness> capabilityConsciousness = CapabilityHandler.capConsciousness;
        Capability.IStorage<IConsciousness> storageConsciousness = capabilityConsciousness.getStorage();
        Capability<IMindActivity> capabilityMindActivity = CapabilityHandler.capMindActivity;
        Capability.IStorage<IMindActivity> storageMindActivity = capabilityMindActivity.getStorage();
        if(event.getOriginal().hasCapability(capabilityConsciousness, null) && event.getEntityPlayer().hasCapability(capabilityConsciousness, null)){
            NBTBase nbt = storageConsciousness.writeNBT(capabilityConsciousness, event.getOriginal().getCapability(capabilityConsciousness, null), null);
            storageConsciousness.readNBT(capabilityConsciousness, event.getEntityPlayer().getCapability(capabilityConsciousness, null), null, nbt);
        }
        if(event.getOriginal().hasCapability(capabilityMindActivity, null) && event.getEntityPlayer().hasCapability(capabilityMindActivity, null)){
            NBTBase nbt = storageMindActivity.writeNBT(capabilityMindActivity, event.getOriginal().getCapability(capabilityMindActivity, null), null);
            storageMindActivity.readNBT(capabilityMindActivity, event.getEntityPlayer().getCapability(capabilityMindActivity, null), null, nbt);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event){
        if (!event.player.world.isRemote)
        {
            EntityPlayer player = event.player;
            if(player.hasCapability(CapabilityHandler.capConsciousness, null)){
                PacketConsciousness message = new PacketConsciousness();
                IConsciousness consciousness = player.getCapability(CapabilityHandler.capConsciousness, null);
                Capability.IStorage<IConsciousness> storage = CapabilityHandler.capConsciousness.getStorage();

                message.compound = new NBTTagCompound();
                message.compound.setTag("consciousness", storage.writeNBT(CapabilityHandler.capConsciousness, consciousness, null));
                EOK.getNetwork().sendTo(message, (EntityPlayerMP) player);
            }
            if(player.hasCapability(CapabilityHandler.capMindActivity, null)){
                PacketMindActivity message = new PacketMindActivity();
                IMindActivity mindActivity = player.getCapability(CapabilityHandler.capMindActivity, null);
                Capability.IStorage<IMindActivity> storage = CapabilityHandler.capMindActivity.getStorage();

                message.compound = new NBTTagCompound();
                message.compound.setTag("mindActivity", storage.writeNBT(CapabilityHandler.capMindActivity, mindActivity, null));
                EOK.getNetwork().sendTo(message, (EntityPlayerMP) player);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if(!event.player.world.isRemote) {
            EntityPlayer player = event.player;
            IConsciousness consciousness = player.getCapability(CapabilityHandler.capConsciousness, null);
            Capability.IStorage<IConsciousness> storage = CapabilityHandler.capConsciousness.getStorage();
            if(player.isPlayerFullyAsleep()){
                consciousness.setConsciousnessValue(100.0D);
            }
            else {
                double conV = consciousness.getConsciousnessValue();
                if(conV >= 0.0D) {
                    consciousness.setConsciousnessValue(conV - 0.005D);
                }
            }
            player.getEntityData().setTag(CapabilityHandler.capConsciousness.getName(), storage.writeNBT(CapabilityHandler.capConsciousness, consciousness, null));
            PacketConsciousness message = new PacketConsciousness();
            message.compound = new NBTTagCompound();
            message.compound.setTag("consciousness", storage.writeNBT(CapabilityHandler.capConsciousness, consciousness, null));
            EOK.getNetwork().sendTo(message, (EntityPlayerMP) player);
        }
    }
}