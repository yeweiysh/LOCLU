function f=localgraphGenerator_varying_cluster_size_intro(n)


clustersize1=ceil((n-10)*rand())+10;
clustersize2=ceil((n-10)*rand())+10;
clustersize3=ceil((n-10)*rand())+10;
clustersize4=ceil((n-10)*rand())+10;

B11=zeros(clustersize1,clustersize1);
for i=1:clustersize1-1
    for j=i+1:clustersize1
        if rand()<=0.35
            B11(i,j)=1;
            B11(j,i)=B11(i,j);
        end
    end
end

B22=zeros(clustersize2,clustersize2);
for i=1:clustersize2-1
    for j=i+1:clustersize2
        if rand()<=0.35
            B22(i,j)=1;
            B22(j,i)=B22(i,j);
        end
    end
end

B33=zeros(clustersize3,clustersize3);
for i=1:clustersize3-1
    for j=i+1:clustersize3
        if rand()<=0.35
            B33(i,j)=1;
            B33(j,i)=B33(i,j);
        end
    end
end

B44=zeros(clustersize4,clustersize4);
for i=1:clustersize4-1
    for j=i+1:clustersize4
        if rand()<=0.35
            B44(i,j)=1;
            B44(j,i)=B44(i,j);
        end
    end
end

B12=zeros(clustersize1,clustersize2);
for i=1:clustersize1
    for j=1:clustersize2
        r=rand();
        if r<=0.01
            B12(i,j)=1;
        end
    end
end

B13=zeros(clustersize1,clustersize3);
for i=1:clustersize1
    for j=1:clustersize3
        r=rand();
        if r<=0.01
            B13(i,j)=1;
        end
    end
end

B14=zeros(clustersize1,clustersize4);
for i=1:clustersize1
    for j=1:clustersize4
        r=rand();
        if r<=0.01
            B14(i,j)=1;
        end
    end
end

B23=zeros(clustersize2,clustersize3);
for i=1:clustersize2
    for j=1:clustersize3
        r=rand();
        if r<=0.01
            B23(i,j)=1;
        end
    end
end

B24=zeros(clustersize2,clustersize4);
for i=1:clustersize2
    for j=1:clustersize4
        r=rand();
        if r<=0.01
            B24(i,j)=1;
        end
    end
end

B34=zeros(clustersize3,clustersize4);
for i=1:clustersize3
    for j=1:clustersize4
        r=rand();
        if r<=0.01
            B34(i,j)=1;
        end
    end
end

label=zeros(n,1);
label1(1:clustersize1,1)=1;
label2(1:clustersize2,1)=2;
label3(1:clustersize3,1)=3;
label4(1:clustersize4,1)=4;
label=[label1;label2;label3;label4];
    

filename=['C:\Users\Desktop\local clustering\synthetic graph data\intro\1\' num2str(n) '\label.mat'];
save(filename,'label');

A=[B11,B12,B13,B14;B12',B22,B23,B24;B13',B23',B33,B34;B14',B24',B34',B44];
filename=['C:\Users\Desktop\local clustering\synthetic graph data\intro\1\' num2str(n) '\A.mat'];
save(filename,'A');
A=sparse(A);

filename=['C:\Users\Desktop\local clustering\synthetic graph data\intro\1\' num2str(n) '\network.dat'];
savesparse(filename, A);




f=A;