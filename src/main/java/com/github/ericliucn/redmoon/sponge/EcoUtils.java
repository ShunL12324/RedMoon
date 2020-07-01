package com.github.ericliucn.redmoon.sponge;

import com.github.ericliucn.redmoon.Main;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.service.economy.transaction.ResultType;

import java.math.BigDecimal;
import java.util.Optional;


// Invoke in server side only
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

    public static boolean withdraw(Player player, String currency, double amount){
        EconomyService service = Sponge.getServiceManager().provideUnchecked(EconomyService.class);
        Currency cur = getCurrency(service, currency);
        Optional<UniqueAccount> optAccount = service.getOrCreateAccount(player.getUniqueId());
        if (!optAccount.isPresent()) return false;

        UniqueAccount account = optAccount.get();
        BigDecimal balance = account.getBalance(cur);
        if (balance.compareTo(BigDecimal.valueOf(amount)) < 0) return false;

        return account.withdraw(cur, BigDecimal.valueOf(amount), Cause.of(EventContext.builder().add(EventContextKeys.PLAYER, player).build(), Main.INSTANCE)).getResult().equals(ResultType.SUCCESS);

    }

    public static boolean deposit(Player player, String currency, double amount){
        EconomyService service = Sponge.getServiceManager().provideUnchecked(EconomyService.class);
        Currency cur = getCurrency(service, currency);
        Optional<UniqueAccount> optAccount = service.getOrCreateAccount(player.getUniqueId());
        if (!optAccount.isPresent()) return false;

        UniqueAccount account = optAccount.get();

        return account.deposit(cur, BigDecimal.valueOf(amount), Cause.of(EventContext.builder().add(EventContextKeys.PLAYER, player).build(), Main.INSTANCE)).getResult().equals(ResultType.SUCCESS);

    }

}
