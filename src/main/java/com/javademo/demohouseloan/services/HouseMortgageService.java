package com.javademo.demohouseloan.services;

import com.javademo.demohouseloan.api.model.Mortgage;
import com.javademo.demohouseloan.api.model.Person;
import com.javademo.demohouseloan.utils.CombGuid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class HouseMortgageService {

    //test in mem data
    private Map<UUID, Mortgage> mortgages = new HashMap<>();

    public HouseMortgageService() {

       /* var mortage = new Mortgage(new ArrayList<Person>() {{
            add(new Person("03073826623", "Ola", "Norman"));
            add(new Person("03073826623", "Ola", "Norman"));
        }}, 200000, "Vi sakl låne penger til hus");
        mortgages.put(CombGuid.newuuid(), mortage);*/

        mortgages.put(CombGuid.newuuid(), new Mortgage(new Person[]{
                new Person("04073826627", "Jhon", "Doe")
        }, 100000, "test lånsøknad"));

        var mortage = new Mortgage(new Person[]{
            new Person("03073826623", "Ola", "Norman"),
            new Person("03073826623", "Ola", "Norman")
        }, 200000, "Vi sakl låne penger til hus");
        mortgages.put(CombGuid.newuuid(), mortage);

        System.out.println(mortgages);
    }

        public Optional<Mortgage> getMortgageById(UUID mortgageId) {
            if(mortgageId == null) return Optional.empty();

            return Optional.of(mortgages.get(mortgageId));
            }

    /*  public CompletableFuture<Optional<Mortgage>> getMortgageById(UUID mortgageId) {
            if(mortgageId == null) return null;

            return CompletableFuture.supplyAsync(() -> {
                //TODO: get from db task
                Optional result = Optional.empty();
                result = Optional.of(mortgages.get(mortgageId));
                return result;
            });
        }*/

    public List<Mortgage> getMortgages() {
        List<Mortgage> result = new ArrayList<>();
        result.addAll(mortgages.values());

        System.out.println(mortgages.keySet());
        return  result;
    }

    public List<Mortgage> getActiveMortgages() {
        List<Mortgage> result = new ArrayList<>();
        for (Mortgage mortgage: mortgages.values()
             ) {
            if(mortgage.getActive()) result.add(mortgage);
        }

        System.out.println(mortgages.keySet());
        return  result;
    }

    public Optional<UUID> createMortgage(Mortgage mortgage) {
        if (mortgage.getPersons().isEmpty() || mortgage.getAmount() == 0) return Optional.empty();

        UUID id = CombGuid.newuuid();
        try{
            mortgages.put(id, mortgage);
        }catch (Exception e){
            //log in logger
            System.out.println("Feil ved lagring av ny lånsøknad" + e);
        }

        return Optional.of(id);
    }

    public Optional<Mortgage> updateMortgage(String mortgageId, Mortgage mortgage) {
        if (mortgageId.isEmpty() || mortgages.get(UUID.fromString(mortgageId)) == null) return Optional.empty();

        var value = mortgages.computeIfPresent(UUID.fromString(mortgageId), (k, v) -> {
            v.setAmount(mortgage.getAmount());
            for (Person per: mortgage.getPersons()
                 ) {
                if(!v.getPersons().contains(per)) v.setPersons(per);
            }

            return v;
        });

        return Optional.of(value);
    }

    public Optional<Boolean> deleteMortgage(UUID mortageId) {
        if (mortageId == null || mortgages.get(mortageId) == null) return Optional.empty();

        mortgages.computeIfPresent(mortageId, (k,v) ->{
            v.setActive(false);
            return v;
        });

        return Optional.of(true);
    }
}
