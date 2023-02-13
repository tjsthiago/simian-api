create table analysis_data (
	id bigserial primary key,
	hash varchar(36) not null unique,
	is_simian_dna boolean not null default false
);