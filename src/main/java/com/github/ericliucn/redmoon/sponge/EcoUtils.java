package com.github.ericliucn.redmoon.sponge;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;

import java.math.BigDecimal;
import java.util.Optional;

public class EcoUtils {

    public static BigDecimal getPlayerBalance(Player player, String currency){
        Optional<EconomyService> optionalEconomyService = Sponge.getServiceManager().provide(EconomyService.class);
        if (!optionalEconomyService.isPresent()){
            Sponge.getServer().getConsole().sendMessage(Utils.toText("&aEconomyService not found!"));
            return BigDecimal.ZERO;
        }

        EconomyService service = optionalEconomyService.get();

        Optional<UniqueAccount> optAccount = service.getOrCreateAccount(player.getUniqueId());

        if (!optAccount.isPresent()){
            return BigDecimal.ZERO;
        }

        UniqueAccount account = optAccount.get();

        return account.getBalance(getCurrency(service, currency));
    }

    public static Currency getCurrency(EconomyService service, String curName){
        for (Currency cur:service.getCurrencies()
             ) {
            if (cur.getDisplayName().toPlain().equalsIgnoreCase(curName)){
                return cur;
            }
        }
        return service.getDefaultCurrency();
    }

}
