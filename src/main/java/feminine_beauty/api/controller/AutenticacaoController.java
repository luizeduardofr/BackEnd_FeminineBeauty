package feminine_beauty.api.controller;

import feminine_beauty.api.domain.cliente.Cliente;
import feminine_beauty.api.domain.endereco.Endereco;
import feminine_beauty.api.domain.usuario.UserRole;
import feminine_beauty.api.dtos.cliente.DadosCadastroCliente;
import feminine_beauty.api.dtos.usuario.DadosAutenticacao;
import feminine_beauty.api.domain.usuario.Usuario;
import feminine_beauty.api.infra.security.DadosTokenJWT;
import feminine_beauty.api.infra.security.TokenService;
import feminine_beauty.api.repositories.ClienteRepository;
import feminine_beauty.api.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var authenticatioToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticatioToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/registro")
    public ResponseEntity<Void> efetuarRegistro(@RequestBody @Valid DadosCadastroCliente dados) {

        if (this.repository.findByLogin(dados.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());

        Usuario novoUsuario = new Usuario(dados.login(), encryptedPassword);
        novoUsuario.setRole(UserRole.USER);
        this.repository.save(novoUsuario);

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(dados.nome());
        novoCliente.setEmail(dados.email());
        novoCliente.setTelefone(dados.telefone());
        novoCliente.setCpf(dados.cpf());
        novoCliente.setEndereco(new Endereco(dados.endereco()));
        novoCliente.setUsuario(novoUsuario);
        novoCliente.setAtivo(true);

        clienteRepository.save(novoCliente);

        return ResponseEntity.ok().build();
    }
}
