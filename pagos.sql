PGDMP     $    ,                {            pagos    12.14    12.14                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    26007    pagos    DATABASE     �   CREATE DATABASE pagos WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE pagos;
                postgres    false            �            1259    26008    pago    TABLE     �  CREATE TABLE public.pago (
    id integer NOT NULL,
    codigo integer,
    varl integer,
    varg integer,
    vars integer,
    pleche integer,
    pgrasa integer,
    psolidos integer,
    bonificacion integer,
    dvarl integer,
    dvarg integer,
    dvars integer,
    retencion integer,
    total integer,
    vfinal integer,
    nombre text,
    fecha text,
    kls integer,
    diasenvio integer,
    promdiario double precision,
    grasa integer,
    solidos integer
);
    DROP TABLE public.pago;
       public         heap    postgres    false            �            1259    26013    pago_id_seq    SEQUENCE     �   ALTER TABLE public.pago ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pago_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    202            �
           2606    26012    pago pago_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.pago DROP CONSTRAINT pago_pkey;
       public            postgres    false    202           