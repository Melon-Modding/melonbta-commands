package prophetsama.testing.commands;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

/**
 * This command
 ***********************************************************************************/

public class Spawn extends Command {
	private final static String COMMAND = "spawn";
	private final static String NAME = "Spawn";

	public Spawn() {
        super(COMMAND);
    }

	@Override
	public boolean execute(CommandHandler commandHandler, CommandSender commandSender, String[] strings) {
		// Teleports player to spawn

		return false;
	}

	@Override
	public boolean opRequired(String[] strings) {
		return false;
	}

	@Override
	public void sendCommandSyntax(CommandHandler commandHandler, CommandSender commandSender) {
		// Feedback to the player that it executed
		commandSender.sendMessage("Teleported " + commandSender.getPlayer().username + " to Spawn");
	}
}