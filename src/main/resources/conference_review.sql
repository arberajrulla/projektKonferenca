PGDMP     *        	            x           conference_review    12.2    12.2 $    9           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            :           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ;           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            <           1262    16557    conference_review    DATABASE     �   CREATE DATABASE conference_review WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
 !   DROP DATABASE conference_review;
                postgres    false            �            1259    16649    artikull    TABLE       CREATE TABLE public.artikull (
    artikull_id integer NOT NULL,
    titulli character varying(255) NOT NULL,
    abstrakti character varying(255) NOT NULL,
    dokumenti_elektronik character varying(255) NOT NULL,
    kontakt character varying NOT NULL
);
    DROP TABLE public.artikull;
       public         heap    postgres    false            �            1259    16647    artikull_artikull_id_seq    SEQUENCE     �   CREATE SEQUENCE public.artikull_artikull_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.artikull_artikull_id_seq;
       public          postgres    false    203            =           0    0    artikull_artikull_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.artikull_artikull_id_seq OWNED BY public.artikull.artikull_id;
          public          postgres    false    202            �            1259    16753    autor    TABLE     �   CREATE TABLE public.autor (
    email_id character varying(255) NOT NULL,
    emri character varying(50) NOT NULL,
    mbiemri character varying(50) NOT NULL,
    artikull_id integer NOT NULL,
    id integer NOT NULL,
    artikuj_artikull_id integer
);
    DROP TABLE public.autor;
       public         heap    postgres    false            �            1259    24993    autor_id_seq    SEQUENCE     �   ALTER TABLE public.autor ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.autor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    206            �            1259    25036    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    24978    login    TABLE     ]  CREATE TABLE public.login (
    emri character varying(50) NOT NULL,
    mbiemri character varying(50) NOT NULL,
    username character varying(50) NOT NULL,
    email character varying(80) NOT NULL,
    password character varying(1000) NOT NULL,
    kategoria character varying NOT NULL,
    nrcel character varying(30) NOT NULL,
    salt bytea
);
    DROP TABLE public.login;
       public         heap    postgres    false            �            1259    16658 	   shqyrtues    TABLE     �   CREATE TABLE public.shqyrtues (
    id_email character varying(255) NOT NULL,
    emri character varying(70) NOT NULL,
    mbiemri character varying(70) NOT NULL,
    institucioni character varying(255) NOT NULL
);
    DROP TABLE public.shqyrtues;
       public         heap    postgres    false            �            1259    16681    shqyrtues_artikull    TABLE     p  CREATE TABLE public.shqyrtues_artikull (
    shqrtid character varying(255) NOT NULL,
    arid integer NOT NULL,
    merita_teknike integer NOT NULL,
    kuptueshmeria integer NOT NULL,
    origjinaliteti integer NOT NULL,
    perkatesi_konference integer NOT NULL,
    rekomandime character varying(255),
    meritateknike integer,
    perkatesikonference integer
);
 &   DROP TABLE public.shqyrtues_artikull;
       public         heap    postgres    false            �            1259    25010    shqyrtues_tema    TABLE     �   CREATE TABLE public.shqyrtues_tema (
    id integer NOT NULL,
    shqyrtues_id character varying(255) NOT NULL,
    temat character varying(255) NOT NULL
);
 "   DROP TABLE public.shqyrtues_tema;
       public         heap    postgres    false            �            1259    25018    shqyrtues_tema_id_seq    SEQUENCE     �   ALTER TABLE public.shqyrtues_tema ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.shqyrtues_tema_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    209            �
           2604    16652    artikull artikull_id    DEFAULT     |   ALTER TABLE ONLY public.artikull ALTER COLUMN artikull_id SET DEFAULT nextval('public.artikull_artikull_id_seq'::regclass);
 C   ALTER TABLE public.artikull ALTER COLUMN artikull_id DROP DEFAULT;
       public          postgres    false    203    202    203            .          0    16649    artikull 
   TABLE DATA           b   COPY public.artikull (artikull_id, titulli, abstrakti, dokumenti_elektronik, kontakt) FROM stdin;
    public          postgres    false    203   �,       1          0    16753    autor 
   TABLE DATA           ^   COPY public.autor (email_id, emri, mbiemri, artikull_id, id, artikuj_artikull_id) FROM stdin;
    public          postgres    false    206   S-       2          0    24978    login 
   TABLE DATA           a   COPY public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) FROM stdin;
    public          postgres    false    207   .       /          0    16658 	   shqyrtues 
   TABLE DATA           J   COPY public.shqyrtues (id_email, emri, mbiemri, institucioni) FROM stdin;
    public          postgres    false    204   *1       0          0    16681    shqyrtues_artikull 
   TABLE DATA           �   COPY public.shqyrtues_artikull (shqrtid, arid, merita_teknike, kuptueshmeria, origjinaliteti, perkatesi_konference, rekomandime, meritateknike, perkatesikonference) FROM stdin;
    public          postgres    false    205   �1       4          0    25010    shqyrtues_tema 
   TABLE DATA           A   COPY public.shqyrtues_tema (id, shqyrtues_id, temat) FROM stdin;
    public          postgres    false    209   �1       >           0    0    artikull_artikull_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.artikull_artikull_id_seq', 6, true);
          public          postgres    false    202            ?           0    0    autor_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.autor_id_seq', 9, true);
          public          postgres    false    208            @           0    0    hibernate_sequence    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hibernate_sequence', 1, true);
          public          postgres    false    211            A           0    0    shqyrtues_tema_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.shqyrtues_tema_id_seq', 5, true);
          public          postgres    false    210            �
           2606    16657    artikull artikull_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.artikull
    ADD CONSTRAINT artikull_pkey PRIMARY KEY (artikull_id);
 @   ALTER TABLE ONLY public.artikull DROP CONSTRAINT artikull_pkey;
       public            postgres    false    203            �
           2606    16757    autor autor_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (email_id);
 :   ALTER TABLE ONLY public.autor DROP CONSTRAINT autor_pkey;
       public            postgres    false    206            �
           2606    24982    login login_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.login
    ADD CONSTRAINT login_pkey PRIMARY KEY (username, email, nrcel);
 :   ALTER TABLE ONLY public.login DROP CONSTRAINT login_pkey;
       public            postgres    false    207    207    207            �
           2606    16685 *   shqyrtues_artikull shqyrtues_artikull_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY public.shqyrtues_artikull
    ADD CONSTRAINT shqyrtues_artikull_pkey PRIMARY KEY (shqrtid, arid);
 T   ALTER TABLE ONLY public.shqyrtues_artikull DROP CONSTRAINT shqyrtues_artikull_pkey;
       public            postgres    false    205    205            �
           2606    16665    shqyrtues shqyrtues_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.shqyrtues
    ADD CONSTRAINT shqyrtues_pkey PRIMARY KEY (id_email);
 B   ALTER TABLE ONLY public.shqyrtues DROP CONSTRAINT shqyrtues_pkey;
       public            postgres    false    204            �
           2606    25017 "   shqyrtues_tema shqyrtues_tema_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.shqyrtues_tema
    ADD CONSTRAINT shqyrtues_tema_pkey PRIMARY KEY (id, temat);
 L   ALTER TABLE ONLY public.shqyrtues_tema DROP CONSTRAINT shqyrtues_tema_pkey;
       public            postgres    false    209    209            �
           2606    16758    autor autor_artikull_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_artikull_id_fkey FOREIGN KEY (artikull_id) REFERENCES public.artikull(artikull_id);
 F   ALTER TABLE ONLY public.autor DROP CONSTRAINT autor_artikull_id_fkey;
       public          postgres    false    206    2720    203            �
           2606    25054 !   autor fk81ignlhmhciei87rf0folbc0j    FK CONSTRAINT     �   ALTER TABLE ONLY public.autor
    ADD CONSTRAINT fk81ignlhmhciei87rf0folbc0j FOREIGN KEY (artikuj_artikull_id) REFERENCES public.artikull(artikull_id);
 K   ALTER TABLE ONLY public.autor DROP CONSTRAINT fk81ignlhmhciei87rf0folbc0j;
       public          postgres    false    203    206    2720            �
           2606    16691 /   shqyrtues_artikull shqyrtues_artikull_arid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.shqyrtues_artikull
    ADD CONSTRAINT shqyrtues_artikull_arid_fkey FOREIGN KEY (arid) REFERENCES public.artikull(artikull_id);
 Y   ALTER TABLE ONLY public.shqyrtues_artikull DROP CONSTRAINT shqyrtues_artikull_arid_fkey;
       public          postgres    false    205    2720    203            �
           2606    16686 2   shqyrtues_artikull shqyrtues_artikull_shqrtid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.shqyrtues_artikull
    ADD CONSTRAINT shqyrtues_artikull_shqrtid_fkey FOREIGN KEY (shqrtid) REFERENCES public.shqyrtues(id_email);
 \   ALTER TABLE ONLY public.shqyrtues_artikull DROP CONSTRAINT shqyrtues_artikull_shqrtid_fkey;
       public          postgres    false    205    204    2722            .   �   x�e��
�0���W���ζ���h�BH@����%��s�9<f��O��0���LG��]He� �ҜT'���B��9�N;�����Q!:Z�y�l����<��H>�+z��v�ۚ��P��3 m�C�      1   �   x�]�M� �׏Sx�&���N�'�
�i�0�P��ޙR��f��k��Q���}ð)q��c���8�~�j�*�*����W��V(R?"E���s�Mʈ̓��Ȧ8��0q�H���/[�>��*I
���;%�i
���[�j���⠣�0Ƽ ��a�      2     x�uT�v�6]s>& _�.�H-���D)uZ۵4cg���<7=c>D��
��%��q}0�����������ez~4H��
!r��yk��v	����u5����x9�����_A� 6۶��ǧ Q����`���xx^? &k?�fZ?�B� 4����y!���Lo�z�
��t:��ϻ^��y7�g��s���~6 |��^��u6�y3�ֶf�6�s�+Tg�v��/�������_�])��߰���vq=m�D��hY���O3Z�EG�8gλ�j]ho����<,�:Yڶ\�.��+�X�|$�,�����䈘��� D2�T�1�bņ b��b#8�>��BL�<wY���֗��u����Zl��R�xˊ%�v����dR��̀4�<�eA'�"6͂!���o�>M�������cR��ks:]o#F�E��*9��łu�'N��U��V*���UwgS�JG��C�\�ف� ���4W9��@�XH*��(��S������[�#�`j��!N�ka�0έ9�]z2ڔ���4](�)Wr�S���f���W9t��!�����y�C����U�"U�k���t�w�=۔��@.�]��W��+�z*w;71"ZHHZ2K�&+Q���}f�yyy1����jд�A��O\
I쬏QP#�L���+��2���C���(��JEUҔ���,AA� �����=%U���Q�
U��bb)@���
��WO'�Ytz�x�nr����9��4��e^v�/����ψM      /   a   x�+�q(��K����P0��MR Ӟy�%�%�ə�y�
�\9�H�����PUq��"�2��2FUe�UZ������U�	Wr"�2S�2STe�\1z\\\ ې<�      0   G   x�+�q(��K���4�4�4bs�?��ɆIqZrZ�J���$���(e��,-N�ɂd̀�!���� %�f      4   e   x�M�+�0 Pݞ����5�Y��2�-� 8=
�}�U������	�Y'�5P~m���B�@��˓��[X�Ǌc"�b�h��/R�)�� �KD| ��(m     