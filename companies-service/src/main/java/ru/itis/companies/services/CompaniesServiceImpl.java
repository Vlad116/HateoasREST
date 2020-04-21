package ru.itis.companies.services;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.companies.models.Company;
import ru.itis.companies.repositories.CompaniesRepository;

import java.util.List;

@Service
public class CompaniesServiceImpl implements CompaniesService {
    @Autowired
    private CompaniesRepository companiesRepository;

    @Override
    public List<Company> getAll() {
        return companiesRepository.findAll();
    }

//    @Override
//    public Company get(Long id) {
//        Company result = companiesRepository.getOne(id);
//
//        if (result instanceof HibernateProxy) {
//            return (Company) ((HibernateProxy)result).getHibernateLazyInitializer().getImplementation();
//        }
//        return result;
//    }
}
