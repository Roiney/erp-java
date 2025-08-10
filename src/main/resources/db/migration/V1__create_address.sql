create table if not exists public.address (
  id uuid primary key,
  cep varchar(10) not null,
  state varchar(2) not null,
  city varchar(100) not null,
  neighborhood varchar(100) not null,
  street varchar(255) not null,
  number varchar(255) not null,
  complement varchar(255),
  ibge_state_code varchar(10),
  ibge_city_code varchar(10)
);

-- (opcional) índice para buscas comuns
create index if not exists idx_address_cep on public.address(cep);
