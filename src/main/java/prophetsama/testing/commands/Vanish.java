package prophetsama.testing.commands;

import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandError;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;
import net.minecraft.core.net.packet.Packet72UpdatePlayerProfile;
import net.minecraft.server.entity.player.EntityPlayerMP;
import prophetsama.testing.mixininterfaces.IVanish;

import java.util.Arrays;

public class Vanish extends Command {
	private final static String COMMAND = "vanish";
	private final static String NAME = "Vanish";
	public Vanish() {
		super(COMMAND);
	}

	@Override
	public boolean execute(CommandHandler handler, CommandSender sender, String[] strings) {
		if (!sender.isPlayer()){
			throw new CommandError("Must be used by a player!");
		}
		EntityPlayerMP player = (EntityPlayerMP)sender.getPlayer();
		if (strings.length == 0){
			sender.sendMessage(String.format("Vanish for player %s is set to %s", sender.getName(), ((IVanish)sender.getPlayer()).melonbta_commands$isVanished()));
			return true;
		}
		if (strings.length == 1){
			try {
				boolean originalVanish = ((IVanish)sender.getPlayer()).melonbta_commands$isVanished();
				boolean vanishState = Boolean.parseBoolean(strings[0]);
				((IVanish)sender.getPlayer()).melonbta_commands$setVanished(vanishState);
				sender.sendMessage(String.format("Vanish for player %s set to %s", sender.getName(), vanishState));
				player.mcServer.playerList.sendPacketToAllPlayers(new Packet72UpdatePlayerProfile(player.username, player.nickname, player.score, player.chatColor, !vanishState, player.isOperator()));
//				if (originalVanish != vanishState){
//					if (vanishState){
//						player.mcServer.playerList.sendPacketToAllPlayers(
//					}
//				}
				return true;
			} catch (Exception e){
				sender.sendMessage(Arrays.toString(e.getStackTrace()));
			}
		}
		return false;
	}

	@Override
	public boolean opRequired(String[] strings) {
		return true;
	}

	@Override
	public void sendCommandSyntax(CommandHandler commandHandler, CommandSender commandSender) {
		commandSender.sendMessage("/vanish <true/false>");
	}
}