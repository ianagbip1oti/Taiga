package me.chill.commands.categories

import me.chill.commands.container.ContainerKeys
import me.chill.commands.container.command
import me.chill.commands.container.commands
import me.chill.utility.green
import me.chill.utility.jda.embed
import me.chill.utility.jda.send
import me.chill.utility.red
import me.chill.utility.yellow
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.entities.MessageEmbed

fun utilityCommands() = commands {
	command("ping") {
		behavior {
			val messageChannel = args[ContainerKeys.Channel] as MessageChannel
			val jda = args[ContainerKeys.Jda] as JDA
			val latency = jda.ping
			messageChannel.send(pingEmbed(latency))
		}
	}

	command("invite") {
		behavior {
			val messageChannel = args[ContainerKeys.Channel] as MessageChannel
			messageChannel.send(inviteEmbed())
		}
	}

	command("source") {
		behavior {
			val messageChannel = args[ContainerKeys.Channel] as MessageChannel
			messageChannel.send(sourceEmbed())
		}
	}
}

private fun pingEmbed(latency: Long): MessageEmbed? {
	var color: Int? = null
	var thumbnail: String? = null

	when {
		latency < 30 -> {
			color = green
			thumbnail = "https://media.giphy.com/media/YzpFZyKJhQbew/giphy.gif"
		}
		latency < 60 -> {
			color = yellow
			thumbnail = "https://media.giphy.com/media/QQDGBVCAlbsxq/giphy.gif"
		}
		latency > 90 -> {
			color = red
			thumbnail = "https://media.giphy.com/media/eSdh2tT2vz5NS/giphy.gif"
		}
	}

	return embed {
		title = "Pong! \uD83C\uDFD3"
		description = "Ping took ${latency}s"
		this.color = pingEmbed@color
		this.thumbnail = pingEmbed@thumbnail
	}
}

private fun inviteEmbed() =
	embed {
		title = "Invite Taiga"
		description = "[Invite me](https://discordapp.com/oauth2/authorize?client_id=482340927709511682&scope=bot&permissions=8) to your server!"
		color = green
		thumbnail = "https://media.giphy.com/media/mnQezxw2sPYM8/giphy.gif"
	}

private fun sourceEmbed() =
	embed {
		title = "Source Code"
		description = "[GitHub repository](https://github.com/woojiahao/Taiga)"
		color = green
		thumbnail = "http://degrassi.wikia.com/wiki/File:Ryuuji-toradora.gif"
	}