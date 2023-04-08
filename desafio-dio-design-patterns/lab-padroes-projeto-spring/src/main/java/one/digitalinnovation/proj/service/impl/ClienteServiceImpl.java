package one.digitalinnovation.proj.service.impl;

import one.digitalinnovation.proj.model.Cliente;
import one.digitalinnovation.proj.model.ClienteRepository;
import one.digitalinnovation.proj.model.Endereco;
import one.digitalinnovation.proj.model.EnderecoRepository;
import one.digitalinnovation.proj.service.ClienteService;
import one.digitalinnovation.proj.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Provider.Service}, ela será tratada como um <b>Singleton</b>.
 *
 * @author Mariana Molina
 */
@Service
public class ClienteServiceImpl implements ClienteService {

//    Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

//    Strategy: Implementar os métodos definidos na interface.
//    Facade: Abstrair integrações com subsistemmas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
//        Buscar todos os Clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
//        Buscar Clientes por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
//        Buscar Cliente por ID, caso exista:
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
//        Verificar se o Endereco do Cliente já existe (pelo CEP).
        if (clienteBd.isPresent()) {
//        Caso não exista, integrar com o ViaCEP e persistir o retorno.
            salvarClienteComCep(cliente);
//        Alterar Cliente, vinculando o Endereco (novo ou existente).
        }
    }

    @Override
    public void deletar(Long id) {
//        Deletar Cliente por ID.
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
//       Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
//        Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
//      Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }
}
