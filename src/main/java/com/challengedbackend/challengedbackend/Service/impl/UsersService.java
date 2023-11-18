package com.challengedbackend.challengedbackend.Service.impl;

import com.challengedbackend.challengedbackend.DTO.User.RegisterDTO;
import com.challengedbackend.challengedbackend.Mapper.RegisterMapper;
import com.challengedbackend.challengedbackend.Model.Account.Account;
import com.challengedbackend.challengedbackend.Model.User.Users;
import com.challengedbackend.challengedbackend.Repository.AccountRepository;
import com.challengedbackend.challengedbackend.Repository.UsersRepository;
import com.challengedbackend.challengedbackend.Service.iUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.*;

@Service
public class UsersService implements iUsersService {



    private final Logger log = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RegisterMapper registerMapper;

    private List<String> alias = new ArrayList<>(Arrays.asList("leon", "tigre", "oso", "aguila", "delfin", "mono", "murcielago", "monito", "oruga", "farmacia", "lechuga"));
    private List<String> subalias = new ArrayList<>(Arrays.asList("rojo", "azul", "verde", "amarillo", "naranja", "nomada", "play", "tarjeta"));

    @Autowired
    public UsersService(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }

    public RegisterDTO create(RegisterDTO usersDTO) {

        Optional<Users> existing_user = usersRepository.findByEmail(usersDTO.getEmail());
        Optional<Users> existing_dni = usersRepository.findBydni(usersDTO.getDni());  // Corregido: findBydni(usersDTO.getDni())

        if (existing_user.isPresent()) {
            throw new RuntimeException("Ya existe un usuario con el mismo email");
        }
        if (existing_dni.isPresent()){
            throw new RuntimeException("Ya existe un usuario con el mismo dni");
        }


            Users users = registerMapper.toModel(usersDTO);

            users = usersRepository.save(users);

            String alias = generateAlias();

            Account cuenta = new Account();
            cuenta.setAlias(alias);
            cuenta.setCvu(generateCVU());
            cuenta.setSaldo(BigDecimal.ZERO);
//            cuenta.setUsuario(users);

            cuenta = accountRepository.save(cuenta);


        users.setCuenta(cuenta);
        usersRepository.save(users);



            return registerMapper.toDto(users);
    }

    private String generateAlias() {
        try {
            if (alias.isEmpty() || subalias.isEmpty()) {
                throw new RuntimeException("No hay más combinaciones disponibles para crear tu alias");
            }

            Random random = new Random();

            String aliass = alias.remove(random.nextInt(alias.size()));
            String subaliass = subalias.remove(random.nextInt(subalias.size()));
            return aliass + "_" + subaliass;
        } catch (Exception e) {
                log.error("Error al generar alias", e);
                throw new RuntimeException("Error al generar alias");
            }

        }

    private String generateCVU() {
        StringBuilder cvu = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            cvu.append(generateRandomFourDigits());
            if (i < 2) {
                cvu.append("-");
            }
        }
        return cvu.toString();
    }

    private String generateRandomFourDigits() {
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
        return String.valueOf(randomNumber);
    }


}